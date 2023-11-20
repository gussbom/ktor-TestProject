package com.gusbom.models

import kotlinx.serialization.Serializable

@Serializable

data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String)

val customers = mutableListOf(
    Customer(id = "1", firstName = "John", lastName = "dumelo"),
    Customer(id = "2", firstName = "Johnson", lastName = "dumebi"),
    Customer(id = "3", firstName = "Jonah", lastName = "giller")
)