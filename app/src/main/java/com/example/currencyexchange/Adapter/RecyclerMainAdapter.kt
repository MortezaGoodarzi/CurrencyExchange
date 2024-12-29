package com.example.currencyexchange.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.currencyexchange.databinding.RecyclerMainItemBinding
import com.example.currencyexchange.remote.Model.ContentModel

class RecyclerMainAdapter(
    private val allData: ArrayList<ContentModel>
) : RecyclerView.Adapter<RecyclerMainAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(
            RecyclerMainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = allData.size


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.setData(allData[position])
    }


    inner class MainViewHolder(
        private val binding: RecyclerMainItemBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: ContentModel) {
            binding.txtLable.text = data.label
            binding.txtPrice.text = "%,d".format((data.price / 10))

        }
    }


}