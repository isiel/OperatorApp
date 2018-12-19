package com.mercadolibre.operatorapp.items

import android.support.v7.widget.RecyclerView
import android.view.View
import com.mercadolibre.operatorapp.model.StockItem
import kotlinx.android.synthetic.main.stock_item_row.view.*

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: StockItem, listener: () -> Unit) {
        itemView.stock_item_row_inventory_id.text = item.inventory_id
        itemView.stock_item_row_stock_quantity.text = item.stock_quantity.toString()
        itemView.setOnClickListener{ listener() }
    }

}