package com.concatadapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.concatadapter.Listener
import com.concatadapter.databinding.InputViewLayoutBinding
import com.concatadapter.model.Field

class InputAdapter(listener: Listener): BaseAdapter(listener) {

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
        val binding = InputViewLayoutBinding.inflate(inflater, parent, false)
        return InputViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val field = fieldList[position]
        if (holder is InputViewHolder) holder.bindData(field)
    }

    override fun getItemCount(): Int {
        return fieldList.size
    }

    inner class InputViewHolder(private val binding: InputViewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(field: Field) {
            binding.addView.isVisible = field.isAddViewEnable
            binding.inputLayout.hint = field.title

            binding.addView.setOnClickListener {
                listener.addField(bindingAdapter, field)
            }
        }
    }
}