package com.project.lovelyfoods.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.lovelyfoods.databinding.ItemFoodsListBinding
import com.project.lovelyfoods.model.Food

class FoodsListAdapter : RecyclerView.Adapter<FoodsListAdapter.FoodsListViewHolder>() {

    var foodsList: List<Food> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsListViewHolder {
        val binding = ItemFoodsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsListViewHolder, position: Int) {
        val food = foodsList[position]
        holder.bind(food)

        holder.itemView.setOnLongClickListener {
            TODO("Ajustar chamada do fragment")
            true
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(food.url))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = foodsList.size

    inner class FoodsListViewHolder(
        private val binding: ItemFoodsListBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(food: Food) {
            food.apply {
                binding.foodName.text = name
            }
        }
    }
}