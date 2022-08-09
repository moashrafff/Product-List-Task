package com.moashrafff.developnetworktask.views.pages.c_Landing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.moashrafff.developnetworktask.data.source.ItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val itemRepository: ItemRepo,
                                        application: Application
) : AndroidViewModel(application) {

    val products = itemRepository.items
    val isLoading = itemRepository.isDataLoading
    val showErrorMessage = itemRepository.showErrorMessage


    fun getProducts() {
        itemRepository.getItems()
    }
}