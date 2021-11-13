package com.concatadapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.concatadapter.data.ApiService
import com.concatadapter.model.Field
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val apiService = ApiService.getApiService()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _erroMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _erroMsg

    private val _fieldList = MutableLiveData<ArrayList<Field>>()
    val fieldList: LiveData<ArrayList<Field>> = _fieldList

    fun getDynamicData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val res = apiService.getDynamicFormData("https://api.npoint.io/fa0d8397070a40627d12")
                if (res.isSuccessful) {
                    // Log.d("TAG","body: ${res.body()}")
                    _fieldList.postValue(res.body()?.fields as ArrayList<Field>)
                } else {
                    Log.e("TAG","body: ${res.message()}")
                    _erroMsg.postValue("Something went wrong")
                }
            } catch (e: Exception) {
                Log.e("TAG","exception: ${e.message}")
                _erroMsg.postValue(e.message)
            }
            _isLoading.postValue(false)
        }
    }
}
