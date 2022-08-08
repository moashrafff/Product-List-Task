package com.moashrafff.developnetworktask.views.pages.c_Landing

import androidx.lifecycle.ViewModel
import com.moashrafff.developnetworktask.data.source.ItemRepo
import javax.inject.Inject

class ItemViewModel @Inject constructor(private val itemRepository: ItemRepo) : ViewModel() {

    val products = itemRepository.items
    val isLoading = itemRepository.isDataLoading
    val showErrorMessage = itemRepository.showErrorMessage


    fun getProducts() {
        itemRepository.getItems()
    }
}