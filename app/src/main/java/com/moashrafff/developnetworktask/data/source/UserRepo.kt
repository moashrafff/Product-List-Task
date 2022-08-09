package com.moashrafff.developnetworktask.data.source

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.moashrafff.developnetworktask.data.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UserRepo @Inject constructor(
    private val application: Application,
    private val userPref: UserPreferences,
    private val db: UserDataBaseClient
) {

    private val _Status = MutableStateFlow("")

    fun register(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.dbDao().insertUser(user)
                userPref.saveUserMail(user.email)
                withContext(Dispatchers.Main) {
                }
            } catch (e: Exception) {
                Toast.makeText(application, "Success", Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

    fun getStatus(userEmail: String): LiveData<String> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _Status.emit(db.dbDao().getStatus(userEmail))
            }
            catch (e:Exception){
            }
        }
        return  _Status.asLiveData()
    }

    suspend fun login(userPhone: String): String =
        runBlocking {
            async { db.dbDao().getPassword(userPhone) }
        }.await()

    fun updateStatus(userEmail: String,userStatus: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.dbDao().updateStatus(userEmail,userStatus)
            }
            catch (e:Exception){
                Log.d("UserRepository", e.message.toString())
            }
        }
    }

}