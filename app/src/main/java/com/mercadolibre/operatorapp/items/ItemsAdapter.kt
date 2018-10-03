package com.mercadolibre.operatorapp.items

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mercadolibre.operatorapp.R
import com.mercadolibre.operatorapp.model.StockItem

class ItemsAdapter(var items: Array<StockItem>, val listener: (String) -> Unit) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.stock_item_row, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item) {
            listener(item.inventory_id)
        }
    }

    fun updateData(newItems: Array<StockItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}