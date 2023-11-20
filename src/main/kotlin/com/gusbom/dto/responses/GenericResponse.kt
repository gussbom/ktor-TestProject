package com.gusbom.dto.responses

import io.ktor.http.*
import kotlinx.serialization.Serializable


@Serializable
data class GenericResponse<T>(
//    val statusCode : String,

    val success: Boolean,
    val message: String,
    val data: T
)