package com.moashrafff.developnetworktask.data.source

import androidx.room.Database
import com.moashrafff.developnetworktask.data.model.User
import com.moashrafff.developnetworktask.interfaces.DataBaseDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBaseClient {

    abstract fun dbDao(): DataBaseDao
}