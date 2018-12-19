package com.mercadolibre.operatorapp.address

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mercadolibre.operatorapp.R
import com.mercadolibre.operatorapp.model.StockItem

class AddressAdapter(var items: Array<StockItem>, val listener: (String) -> Unit) : RecyclerView.Adapter<AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.address_list_row, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item) {
            listener(item.address_id)
        }
    }
}