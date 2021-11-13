package com.concatadapter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.concatadapter.Listener
import com.concatadapter.databinding.ImageFieldLayoutBinding
import com.concatadapter.model.Field

class ImageAdapter(listener: Listener): BaseAdapter(listener) {

    private val fieldList = ArrayList<Field>()

    fun addField(field: Field){
        fieldList.add(field)
    }

    fun setFiledList(list: ArrayList<Field>){
        fieldList.clear()
        fieldList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageFieldLayoutBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val field = fieldList[position]
        if (holder is ImageViewHolder) holder.bindData(field)
    }

    override fun getItemCount(): Int {
        return fieldList.size
    }

    inner class ImageViewHolder(private val binding: ImageFieldLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(field: Field) {
            Log.d("TAG","position:$bindingAdapterPosition, ${field.isAddViewEnable}")
            binding.addView.isVisible = field.isAddViewEnable
            binding.title.text = field.title
            binding.addView.setOnClickListener {
                listener.addField(bindingAdapter, field)
            }
        }
    }
}