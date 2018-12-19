package com.mercadolibre.operatorapp.address

import android.support.v7.widget.RecyclerView
import android.view.View
import com.mercadolibre.operatorapp.model.StockItem
import kotlinx.android.synthetic.main.address_list_row.view.*

class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: StockItem, listener: () -> Unit) {
        itemView.address_list_row_id.text = item.address_id
        itemView.address_list_row_quantity.text = item.stock_quantity.toString()
        itemView.setOnClickListener{ listener() }
    }

}