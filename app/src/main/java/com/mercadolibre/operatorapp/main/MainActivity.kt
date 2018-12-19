package com.mercadolibre.operatorapp.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.mercadolibre.operatorapp.R
import com.mercadolibre.operatorapp.base.BaseActivity
import com.mercadolibre.operatorapp.items.ItemListActivity
import com.mercadolibre.operatorapp.model.StockItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    companion object {
        const val DATA_KEY = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items_input.setText("RR-001-000-00-00")

        items_search_action_button.setOnClickListener {
            doSearch(items_input.text.toString())
        }
    }

    private fun doSearch(address: String) {
        val call: Call<Array<StockItem>> = api.getStock(address)
        call.enqueue(object : Callback<Array<StockItem>> {

            override fun onResponse(call: Call<Array<StockItem>>, response: Response<Array<StockItem>>) {
                val intent = Intent(this@MainActivity, ItemListActivity::class.java)
                intent.putExtra(DATA_KEY, response.body())
                intent.putExtra(ItemListActivity.ADDRESS_KEY, items_input.text.toString())
                startActivity(intent)
            }

            override fun onFailure(call: Call<Array<StockItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "FAILURE!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
