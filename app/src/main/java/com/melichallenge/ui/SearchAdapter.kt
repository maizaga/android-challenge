package com.melichallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melichallenge.databinding.ListItemSearchBinding
import com.melichallenge.model.ItemHeader

/**
 *
 * Created by maizaga on 2019-06-05.
 */
class SearchAdapter(private val onClick: (ItemHeader) -> Unit): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var items = arrayListOf<ItemHeader>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder = SearchViewHolder(
        ListItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemSearchBinding.item = items[position]
        holder.itemSearchBinding.searchItemContainer.setOnClickListener { onClick(items[position]) }
        holder.itemSearchBinding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    class SearchViewHolder(val itemSearchBinding: ListItemSearchBinding):
        RecyclerView.ViewHolder(itemSearchBinding.root)
}