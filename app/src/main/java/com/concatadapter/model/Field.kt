package com.concatadapter.model

data class Field(
    var id: Int,
    val viewType: String,
    var title: String,
    var isAddViewEnable: Boolean = false,
    val childList: ArrayList<Field>? = ArrayList()
)
