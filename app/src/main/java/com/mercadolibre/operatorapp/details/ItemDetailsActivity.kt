package com.mercadolibre.operatorapp.details

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mercadolibre.operatorapp.R
import com.mercadolibre.operatorapp.address.AddressAdapter
import com.mercadolibre.operatorapp.base.BaseActivity
import com.mercadolibre.operatorapp.items.ItemListActivity
import com.mercadolibre.operatorapp.model.ItemDetails
import com.mercadolibre.operatorapp.model.StockItem
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDetailsActivity : BaseActivity() {

    companion object {
        const val INVENTORY_ID_KEY = "inventory_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val inventoryId: String? = intent.extras?.getString(INVENTORY_ID_KEY)

        inventoryId?.apply {
            getItemDetails(this)
            getAddressList(this)
        }

    }

    private fun getItemDetails(inventoryId: String) {
        val call: Call<ItemDetails> = api.getItemDetails(inventoryId)
        call.enqueue(object : Callback<ItemDetails> {

            override fun onResponse(call: Call<ItemDetails>, response: Response<ItemDetails>) {
                response.body()?.apply {
                    setupView(this)
                }
            }

            override fun onFailure(call: Call<ItemDetails>, t: Throwable) {
                Toast.makeText(this@ItemDetailsActivity, "FAILURE!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getAddressList(inventoryId: String) {
        val call: Call<Array<StockItem>> = api.getAddressesOfInventoryId(inventoryId)
        call.enqueue(object : Callback<Array<StockItem>> {

            override fun onResponse(call: Call<Array<StockItem>>, response: Response<Array<StockItem>>) {
                response.body()?.apply {
                    setupList(this)
                }
            }

            override fun onFailure(call: Call<Array<StockItem>>, t: Throwable) {
                Toast.makeText(this@ItemDetailsActivity, "FAILURE!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun setupView(itemDetails: ItemDetails) {
        item_details_image.setImageURI(itemDetails.pictures[0].url)
        item_details_price.text = itemDetails.price.toString()
        item_details_inventory_id.text = itemDetails.inventory_id
        item_details_title.text = itemDetails.title
    }

    fun setupList(addressList: Array<StockItem>) {
        adresses_recycler_view.adapter = AddressAdapter(addressList) {
            goToItemList(it)
        }

        adresses_recycler_view.addItemDecoration(DividerItemDecoration(this@ItemDetailsActivity,
                LinearLayoutManager.VERTICAL))
    }

    private fun goToItemList(addressId: String) {
        val intent = Intent(this, ItemListActivity::class.java)
        intent.putExtra(ItemListActivity.ADDRESS_KEY, addressId)
        startActivity(intent)
    }

}
