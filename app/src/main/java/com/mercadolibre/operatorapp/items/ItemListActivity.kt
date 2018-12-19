package com.mercadolibre.operatorapp.items

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mercadolibre.operatorapp.R
import com.mercadolibre.operatorapp.base.BaseActivity
import com.mercadolibre.operatorapp.details.ItemDetailsActivity
import com.mercadolibre.operatorapp.main.MainActivity
import com.mercadolibre.operatorapp.model.StockItem
import kotlinx.android.synthetic.main.activity_item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListActivity : BaseActivity() {

    companion object {
        const val ADDRESS_KEY = "address_key";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val data = intent.extras?.getSerializable(MainActivity.DATA_KEY) as? Array<StockItem>
        val addressId: String? = intent.extras?.getString(ADDRESS_KEY)

        item_list_address_id.text = addressId

        if (data != null) {
            setupRecyclerView(data)
        } else {
            addressId?.apply {
                doSearch(this)
            }
        }
    }

    private fun doSearch(address: String) {
        val call: Call<Array<StockItem>> = api.getStock(address)
        call.enqueue(object : Callback<Array<StockItem>> {

            override fun onResponse(call: Call<Array<StockItem>>, response: Response<Array<StockItem>>) {
                setupRecyclerView(response.body()!!)
            }

            override fun onFailure(call: Call<Array<StockItem>>, t: Throwable) {
                Toast.makeText(this@ItemListActivity, "FAILURE!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView(data: Array<StockItem>) {
        items_recycler_view.adapter = ItemsAdapter(data) {
            goToItemDetails(it)
        }

        items_recycler_view.addItemDecoration(DividerItemDecoration(this@ItemListActivity,
                LinearLayoutManager.VERTICAL))
    }

    private fun goToItemDetails(inventoryId: String) {
        val intent = Intent(this, ItemDetailsActivity::class.java)
        intent.putExtra(ItemDetailsActivity.INVENTORY_ID_KEY, inventoryId)
        startActivity(intent)
    }
}
