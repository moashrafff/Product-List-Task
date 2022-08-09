package com.moashrafff.developnetworktask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: String,
    val name: String,
    val phone: String,
    val password: String,
    val token: String,
    val email: String,
    val status: String,
) {
}