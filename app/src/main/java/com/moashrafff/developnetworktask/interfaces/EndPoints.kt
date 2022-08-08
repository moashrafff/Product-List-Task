package com.moashrafff.developnetworktask.interfaces

import com.moashrafff.developnetworktask.data.model.Root
import retrofit2.http.GET

interface EndPoints {

    @GET("products")
    suspend fun getItems(): Root
}