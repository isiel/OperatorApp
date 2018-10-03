package com.mercadolibre.operatorapp.model

data class ItemPicture(val url: String)

data class ItemDetails(val inventory_id: String,
                       val title: String,
                       val pictures: List<ItemPicture>,
                       val price: Double)


