package com.gusbom.models

import kotlinx.serialization.Serializable

@Serializable

data class Cust(val id: String, val firstName: String, val lastName: String)

val customer = mutableListOf<Cust>()