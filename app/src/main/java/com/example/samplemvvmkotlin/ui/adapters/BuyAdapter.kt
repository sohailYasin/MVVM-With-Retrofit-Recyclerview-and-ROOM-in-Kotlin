package com.example.samplemvvmkotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvmkotlin.databinding.AdapterBuyBinding
import com.example.samplemvvmkotlin.models.BuyModel


class BuyAdapter : RecyclerView.Adapter<BuyViewHolder>() {

    var buyItemList = mutableListOf<BuyModel>()
    fun setBuyList(buyItemList: List<BuyModel>) {
        this.buyItemList = buyItemList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBuyBinding.inflate(inflater, parent, false)
        return BuyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        val buyer = buyItemList[position]
        holder.binding.name.text = "Name: " + buyer.name
        holder.binding.price.text = "Price: " + buyer.price
        holder.binding.quantity.text = "Quantity: " + buyer.quantity
    }

    override fun getItemCount(): Int {
        return buyItemList.size
    }
}

class BuyViewHolder(val binding: AdapterBuyBinding) : RecyclerView.ViewHolder(binding.root) {
}