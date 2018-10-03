package com.mercadolibre.operatorapp.details

import android.os.Bundle
import android.widget.Toast
import com.mercadolibre.operatorapp.R
import com.mercadolibre.operatorapp.base.BaseActivity
import com.mercadolibre.operatorapp.model.ItemDetails
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

    fun setupView(itemDetails: ItemDetails) {
        item_details_image.setImageURI(itemDetails.pictures[0].url)
        item_details_price.text = itemDetails.price.toString()
        item_details_inventory_id.text = itemDetails.inventory_id
        item_details_title.text = itemDetails.title
    }
}
