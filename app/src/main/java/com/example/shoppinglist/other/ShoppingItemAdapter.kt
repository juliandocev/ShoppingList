package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
):RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(binding: ShoppingItemBinding):RecyclerView.ViewHolder(binding.root){
        val tvName = binding?.tvName
        val tvAmount = binding?.tvAmount
        val ivDelete = binding?.ivDelete
        val ivMinus = binding?.ivMinus
        val ivPlus = binding?.ivPlus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.tvName.text = curShoppingItem.name
        holder.tvAmount.text= "${curShoppingItem.amount}"

        // Delete the entire current item
        holder.ivDelete.setOnClickListener{
            viewModel.delete(curShoppingItem)
        }
        // Add more quantity of this shopping item
        holder.ivPlus.setOnClickListener{
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }
        // Remove quantity of this shopping item
        holder.ivMinus.setOnClickListener{
            if(curShoppingItem.amount > 0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}