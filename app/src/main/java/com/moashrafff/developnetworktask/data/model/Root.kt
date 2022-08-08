package com.moashrafff.developnetworktask.data.model

data class Root(
    val products: MutableList<Product>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)