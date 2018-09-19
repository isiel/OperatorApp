package com.mercadolibre.operatorapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mercadolibre.operatorapp.api.ApiInterface
import com.mercadolibre.operatorapp.items.ItemsAdapter
import com.mercadolibre.operatorapp.model.StockItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val api: ApiInterface

    init {
        //TODO: put base url
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build()

        api = retrofit.create(ApiInterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items_input.setText("RR-001-000-00-00")

        items_search_action_button.setOnClickListener {
            doSearch(items_input.text.toString())
        }

        items_recycler_view.adapter = ItemsAdapter(emptyArray())
        items_recycler_view.layoutManager = LinearLayoutManager(this)
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
                Toast.makeText(this@MainActivity, "FAILURE!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
