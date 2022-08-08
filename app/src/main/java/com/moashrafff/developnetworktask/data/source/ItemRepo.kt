package com.moashrafff.developnetworktask.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.moashrafff.developnetworktask.data.model.Product
import com.moashrafff.developnetworktask.interfaces.EndPoints
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemRepo @Inject constructor(private val endPoint: EndPoints) {

    private val _isDataLoading = MutableStateFlow(true)
    val isDataLoading: LiveData<Boolean>
        get() = _isDataLoading.asLiveData()

    private val _showErrorMessage = MutableStateFlow(false)
    val showErrorMessage: LiveData<Boolean>
        get() = _showErrorMessage.asLiveData()

    private val _items = MutableStateFlow<MutableList<Product>>(mutableListOf())
    val items: LiveData<MutableList<Product>>
        get() = _items.asLiveData()


    fun getItems() {
        CoroutineScope(Dispatchers.IO).launch {
            _isDataLoading.emit(true)

            try {
                _items.emit(endPoint.getItems().products)
                _isDataLoading.emit(false)
            } catch (e: Exception) {
                _isDataLoading.emit(false)
                _showErrorMessage.emit(true)
            }
        }
    }


}