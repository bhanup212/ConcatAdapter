package com.concatadapter

import androidx.recyclerview.widget.RecyclerView
import com.concatadapter.model.Field

interface Listener {
    fun addField(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?, field: Field)
}