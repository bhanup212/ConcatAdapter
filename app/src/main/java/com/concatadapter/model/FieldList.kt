package com.concatadapter.model

data class FieldList(
    val status: Boolean,
    val fields: List<Field>
){
    fun getFieldList(): ArrayList<Field> {
        val list = ArrayList<Field>()
        fields.forEach {
            list.add(it)
            it.childList?.let { it1 -> list.addAll(it1) }
        }
        return list
    }
}
