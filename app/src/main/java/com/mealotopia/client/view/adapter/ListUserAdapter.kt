package com.mealotopia.client.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mealotopia.client.R
import com.mealotopia.client.databinding.ItemRvUserLayoutBinding
import com.mealotopia.client.model.data_class.user.DataUser

class ListUserAdapter(
    var data: MutableList<DataUser>
): RecyclerView.Adapter<ListUserAdapter.ItemHolder>() {

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: DataUser) = with(itemView){
            val binding = ItemRvUserLayoutBinding.bind(itemView)
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.avatar)
                    .into(ivUser)

                tvFullNameUser.text = item.first_name + " " +item.last_name
                tvEmailUser.text = item.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_user_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data.get(position))
    }

    fun addItem(listUser: List<DataUser>){
        val startPosition = data.size
        data.addAll(listUser)
        notifyItemRangeInserted(startPosition, listUser.size)
    }
}