package com.mercadolibre.operatorapp.api

import com.mercadolibre.operatorapp.model.StockItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiInterface {

    @GET("wms/warehouses/BRTW01/addresses/{address_id}/inventories")
    fun getStock(@Path("address_id") addressId: String): Call<Array<StockItem>>

}
