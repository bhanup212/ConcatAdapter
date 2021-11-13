package com.concatadapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concatadapter.Listener
import com.concatadapter.databinding.LabelFieldLayoutBinding
import com.concatadapter.model.Field

class LabelAdapter(listener: Listener) : BaseAdapter(listener) {

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
        val binding = LabelFieldLayoutBinding.inflate(inflater, parent, false)
        return LabelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val field = fieldList[position]
        if (holder is LabelViewHolder) holder.bindData(field)
    }

    override fun getItemCount(): Int {
        return fieldList.size
    }

    class LabelViewHolder(private val binding: LabelFieldLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(field: Field) {
            binding.labelTv.text = field.title
        }
    }
}
