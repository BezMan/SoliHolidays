package com.example.soliapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soliapp.data.models.Holiday
import com.example.soliapp.databinding.ListItemBinding

class MainListAdapter internal constructor(private val listener: OnItemClickListener) :
    ListAdapter<Holiday, MainListAdapter.HolidayViewHolder>(DIFF_CALLBACK) {


    inner class HolidayViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Holiday) {
            // Bind holiday data to views in the layout
            binding.apply {
                nameTextView.text = item.name
                dateTextView.text = item.date
                checkboxToggleButton.isChecked = item.isFavorite
                checkboxToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    listener.onToggleFavoriteClick(item, isChecked)
                }
                itemLayout.setOnClickListener {
                    listener.onItemTextClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HolidayViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ListItemBinding.inflate(layoutInflater, viewGroup, false)
        return HolidayViewHolder(binding)
    }

    internal fun getItemAt(position: Int): Holiday {
        return getItem(position)
    }

    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int) {
        val item = getItemAt(position)
        holder.bind(item)
    }


    interface OnItemClickListener {
        fun onItemTextClick(item: Holiday)
        fun onToggleFavoriteClick(item: Holiday, isChecked: Boolean)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Holiday>() {
            override fun areItemsTheSame(old: Holiday, new: Holiday): Boolean {
                return old.localName == new.localName && old.name == new.name && old.date == new.date
            }

            override fun areContentsTheSame(old: Holiday, new: Holiday): Boolean {
                return false
            }
        }
    }

    //methods preventing click on an item affecting different items//
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    //methods preventing click on an item affecting different items//


}
