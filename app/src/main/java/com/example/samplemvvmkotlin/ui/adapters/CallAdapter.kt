package com.example.samplemvvmkotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemvvmkotlin.databinding.AdapterCallsBinding
import com.example.samplemvvmkotlin.models.CallListModel

class CallAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var callList = mutableListOf<CallListModel>()
    fun setCallsList(callList: List<CallListModel>) {
        this.callList = callList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCallsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val caller = callList[position]
        holder.binding.name.text = "Name: " + caller.name
        holder.binding.number.text = "Number: " + caller.number

    }

    override fun getItemCount(): Int {
        return callList.size
    }
}

class MainViewHolder(val binding: AdapterCallsBinding) : RecyclerView.ViewHolder(binding.root) {
}