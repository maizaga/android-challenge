package com.melichallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melichallenge.databinding.GridItemAttributeBinding
import com.melichallenge.model.Attribute

/**
 *
 * Created by maizaga on 2019-06-06.
 */
class AttributesAdapter: RecyclerView.Adapter<AttributesAdapter.ViewHolder>() {
    var items = arrayListOf<Attribute>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        GridItemAttributeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gridItemAttributeBinding.attribute = items[position]
        holder.gridItemAttributeBinding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val gridItemAttributeBinding: GridItemAttributeBinding):
        RecyclerView.ViewHolder(gridItemAttributeBinding.root)
}