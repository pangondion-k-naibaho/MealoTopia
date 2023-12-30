package com.mealotopia.client.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mealotopia.client.R
import com.mealotopia.client.databinding.ItemRvMealLayoutBinding
import com.mealotopia.client.model.data_class.meal.DetailMealResponse

class ListMealAdapter(
    var data: MutableList<DetailMealResponse>,
    private val listener: ItemListener
): RecyclerView.Adapter<ListMealAdapter.ItemHolder>(){
    private val TAG = ListMealAdapter::class.java.simpleName
    interface ItemListener{
        fun onItemClicked(item: DetailMealResponse)
    }

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: DetailMealResponse, listener: ItemListener) = with(itemView){
            val binding = ItemRvMealLayoutBinding.bind(itemView)
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.strMealThumb)
                    .into(ivMeal)

                tvMealName.text = item.strMeal
                tvMealCategory.text = item.strCategory
                tvMealInstructions.text = item.strInstructions

                root.setOnClickListener { listener.onItemClicked(item) }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_meal_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data.get(position), listener)
    }

    fun addItem(listMeal: List<DetailMealResponse>){
        val startPosition = data.size
        data.addAll(listMeal)
        notifyItemRangeInserted(startPosition, listMeal.size)
    }

    fun updateItem(newData: List<DetailMealResponse>?) {
        try {
            if(newData != null){
                data.clear() // Clear existing data
                data.addAll(newData) // Add new data
                notifyDataSetChanged() // Notify the adapter that the data has changed
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun getResourceId(imageView: ImageView): Int {
        return try {
            val field = ImageView::class.java.getDeclaredField("mResource")
            field.isAccessible = true
            field.getInt(imageView)
        } catch (e: Exception) {
            -1
        }
    }
}