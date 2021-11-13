package com.concatadapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concatadapter.Listener
import com.concatadapter.databinding.ButtonFieldLayoutBinding
import com.concatadapter.model.Field

class ButtonAdapter(listener: Listener): BaseAdapter(listener) {

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
        val binding = ButtonFieldLayoutBinding.inflate(inflater, parent, false)
        return ButtonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val field = fieldList[position]
        if (holder is ButtonViewHolder) holder.bindData(field)
    }

    override fun getItemCount(): Int {
        return fieldList.size
    }

    class ButtonViewHolder(private val binding: ButtonFieldLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(field: Field) {
            binding.button.hint = field.title
        }
    }
}