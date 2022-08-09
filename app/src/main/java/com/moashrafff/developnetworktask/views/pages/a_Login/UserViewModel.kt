package com.moashrafff.developnetworktask.views.pages.a_Login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moashrafff.developnetworktask.data.model.User
import com.moashrafff.developnetworktask.data.source.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepo: UserRepo , application: Application) : AndroidViewModel(application) {

    fun register(user: User) {
        userRepo.register(user)
    }

    fun getStatus(email: String): LiveData<String> {
        return userRepo.getStatus(email)
    }

    fun updateStatus(email: String,status: String) {
        userRepo.updateStatus(email,status)
    }

    suspend fun login(userPhone: String): String = userRepo.login(userPhone)

}