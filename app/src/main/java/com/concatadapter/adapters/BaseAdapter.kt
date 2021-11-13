package com.concatadapter.adapters

import androidx.recyclerview.widget.RecyclerView
import com.concatadapter.Listener

abstract class BaseAdapter(protected val listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
