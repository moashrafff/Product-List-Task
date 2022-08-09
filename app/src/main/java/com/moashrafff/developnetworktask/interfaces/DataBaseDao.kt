package com.moashrafff.developnetworktask.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moashrafff.developnetworktask.data.model.User

@Dao
interface DataBaseDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("select status FROM user where email = :userEmail")
    fun getStatus(userEmail: String): String

    @Query("select password FROM user where phone = :userPhone")
    suspend fun getPassword(userPhone: String): String

    @Query("select token FROM user where email = :userEmail")
    fun getToken(userEmail: String): String


    @Query("update user SET status=:status where email = :userEmail")
    fun updateStatus(userEmail: String, status: String)




}