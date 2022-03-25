package com.example.lab1.ui.main

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R
import com.example.lab1.data.CurrencyData
import com.example.lab1.databinding.ItemBinding

import android.widget.ImageView




class CurrencyAdapter(private val itemClickListener: ItemClickListener)
    : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var currencies = mutableListOf<CurrencyData>()

    fun set(currencies: List<CurrencyData>){
        this.currencies = currencies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater,parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        currencies.getOrNull(position)?.let{
            var currency = it
            holder.binding.firstCurrencyName.text = currency.name
            holder.binding.price.text = currency.exchangeRate.toString()

            holder.binding.item.setOnClickListener {
                itemClickListener.showCurrencyExchangeView(currency)
            }
        }
    }

    override fun getItemCount(): Int {
        return currencies.count()
    }

    class CurrencyViewHolder(var binding: ItemBinding): RecyclerView.ViewHolder(binding.root)
}