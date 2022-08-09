package com.moashrafff.developnetworktask.data.model

import androidx.room.Entity

@Entity(tableName = "user")
data class User(val name: String,
                val phone: String,
                val password: String,
                val token: String,
                val email:String,
                val status:String,
) {
}