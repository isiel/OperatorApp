package com.mercadolibre.operatorapp.api

import com.mercadolibre.operatorapp.model.StockItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiInterface {

    //@Headers("X-Auth-Token: 1d38a51a306bbb316a05b8d2442b82cedbe01dabf7a93366d688bbfeeb8fe42f")
    @GET("wms/warehouses/BRTW01/addresses/{address_id}/inventories")
    fun getStock(@Path("address_id") addressId: String): Call<Array<StockItem>>

}
