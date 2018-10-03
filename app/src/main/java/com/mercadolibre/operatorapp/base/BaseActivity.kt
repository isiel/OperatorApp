package com.mercadolibre.operatorapp.base

import android.support.v7.app.AppCompatActivity
import com.mercadolibre.operatorapp.api.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseActivity : AppCompatActivity() {

    val api: ApiInterface

    init {
        //TODO: put base url
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build()

        api = retrofit.create(ApiInterface::class.java)
    }

}
