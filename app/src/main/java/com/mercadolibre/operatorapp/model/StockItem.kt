package com.mercadolibre.operatorapp.model

import java.io.Serializable

data class StockItem(val warehouse_id: String,
                     val inventory_id: String,
                     val stock_quantity: Int,
                     val reserved_quantity: Int,
                     val available_quantity: Int,
                     val address_id: String): Serializable