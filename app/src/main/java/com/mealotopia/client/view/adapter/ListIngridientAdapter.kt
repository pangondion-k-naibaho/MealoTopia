package com.mealotopia.client.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mealotopia.client.R
import com.mealotopia.client.databinding.ItemRvIngridientsLayoutBinding
import com.mealotopia.client.model.data_class.meal.MealIngridients

class ListIngridientAdapter(
    var data: MutableList<MealIngridients>
): RecyclerView.Adapter<ListIngridientAdapter.ItemHolder>() {

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: MealIngridients) = with(itemView){
            val binding = ItemRvIngridientsLayoutBinding.bind(itemView)
            binding.apply {
                tvIngridient.text = item.ingridientName
                tvMeasure.text = "(${item.ingridientMeasure})"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_ingridients_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data.get(position))
    }

}