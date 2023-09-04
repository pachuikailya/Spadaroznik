package com.lich.minskattractions

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lich.minskattractions.databinding.ItemBinding
import com.squareup.picasso.Picasso

class SightAdapter(val listener:Listener):RecyclerView.Adapter<SightAdapter.SightViewHolder>() {
    var listFirst= emptyList<Item>()
    class SightViewHolder (view: View):RecyclerView.ViewHolder(view){
        val binding= ItemBinding.bind(view)
        fun bind(item: Item, context:Context,listener:Listener) {
            Picasso.with(context).load(item.image).into(binding.photo)
            binding.tvName.text=item.name
            itemView.setOnClickListener{
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SightViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return SightViewHolder(view)
    }
    override fun onBindViewHolder(holder: SightViewHolder, position: Int) {
            holder.bind(listFirst[position],holder.itemView.context,listener)

    }

    override fun getItemCount(): Int {
        return listFirst.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<Item>){
        listFirst=list
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(item:Item){

        }
    }
}