package com.moashrafff.developnetworktask.data.source

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.moashrafff.developnetworktask.data.model.User
import javax.inject.Inject

class UserPreferences @Inject constructor(application: Application) {


    var userPreferences: SharedPreferences = application.getSharedPreferences("email",
        Context.MODE_PRIVATE
    )
    var editor: SharedPreferences.Editor = userPreferences.edit()



    fun saveUserMail(email: String){
        editor.putString("email", email)
        editor.apply()

    }

    fun getUserMail():String{
        return userPreferences.getString("email", "").toString()
    }




}