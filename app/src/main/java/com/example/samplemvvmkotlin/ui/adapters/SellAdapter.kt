package com.example.samplemvvmkotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvmkotlin.databinding.AdapterBuyBinding
import com.example.samplemvvmkotlin.db.Sell


class SellAdapter : RecyclerView.Adapter<SellViewHolder>() {

    var sellItemList = mutableListOf<Sell>()
    fun setSellList(sellItemList: List<Sell>) {
        this.sellItemList = sellItemList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBuyBinding.inflate(inflater, parent, false)
        return SellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SellViewHolder, position: Int) {
        val buyer = sellItemList[position]
        holder.binding.name.text = "Name: " + buyer.name
        holder.binding.price.text = "Price: " + buyer.price
        holder.binding.quantity.text = "Quantity: " + buyer.quantity
    }

    override fun getItemCount(): Int {
        return sellItemList.size
    }
}

class SellViewHolder(val binding: AdapterBuyBinding) : RecyclerView.ViewHolder(binding.root) {
}