package com.mercadolibre.operatorapp.model

data class StockItem(val warehouse_id: String,
                     val inventory_id: String,
                     val stock_quantity: Int,
                     val reserved_quantity: Int,
                     val available_quantity: Int)