package com.mercadolibre.operatorapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mercadolibre.operatorapp.base.BaseActivity
import com.mercadolibre.operatorapp.details.ItemDetailsActivity
import com.mercadolibre.operatorapp.items.ItemsAdapter
import com.mercadolibre.operatorapp.model.StockItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items_input.setText("RR-001-000-00-00")

        items_search_action_button.setOnClickListener {
            doSearch(items_input.text.toString())
        }

        items_recycler_view.adapter = ItemsAdapter(emptyArray()) {
            goToItemDetails(it)
        }

        items_recycler_view.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    private fun goToItemDetails(inventoryId: String) {
        val intent = Intent(this, ItemDetailsActivity::class.java)
        intent.putExtra(ItemDetailsActivity.INVENTORY_ID_KEY, inventoryId)
        startActivity(intent)
    }

    private fun doSearch(address: String) {
        val call: Call<Array<StockItem>> = api.getStock(address)
        call.enqueue(object : Callback<Array<StockItem>> {

            override fun onResponse(call: Call<Array<StockItem>>, response: Response<Array<StockItem>>) {
                response.body()?.apply {
                    (items_recycler_view.adapter as ItemsAdapter).updateData(this)
                }
                
            }

            override fun onFailure(call: Call<Array<StockItem>>, t: Throwable) {
                Toast.makeText(this@ItemListActivity, "FAILURE!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
