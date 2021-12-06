package com.example.ca1_makeup_arintoul

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.ca1_makeup_arintoul.data.ProductEntity
import com.example.ca1_makeup_arintoul.databinding.ListItemBinding

//this class will extend itself from another class RecyclerView.Adapter

//RE-WATCH THE LAST TWO VIDEOS OF TWO TO ADD NOTES HERE.

class ProductsListAdapter(private val productsList: List<ProductEntity>,
    private val listener: ListItemListener) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>(){

    val selectedProducts = arrayListOf<ProductEntity>()


    inner class ViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView){
        val binding = ListItemBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = productsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val product = productsList[position]
        with(holder.binding){

            productText.text = product.name
            root.setOnClickListener{
                listener.onItemClick(product.id)
            }
            fab.setOnClickListener{
                if(selectedProducts.contains(product)) {
                    selectedProducts.remove(product)
                    fab.setImageResource(R.drawable.ic_makeup_women)
                }else {
                    selectedProducts.add(product)
                    fab.setImageResource(R.drawable.ic_check)
                }
                listener.onItemSelectionChanged()
            }
            fab.setImageResource(
                if(selectedProducts.contains(product)) {
                    R.drawable.ic_check
                } else {
                    R.drawable.ic_makeup_women
                }
            )
        }
    }
    interface ListItemListener {
        fun onItemClick(productId: Int)
        fun onItemSelectionChanged()
    }



}