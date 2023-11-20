package com.gusbom.services

import com.gusbom.dto.requests.RegistrationRequest
import com.gusbom.dto.responses.GenericResponse

interface UserService {
    suspend fun registerUser(params: RegistrationRequest): GenericResponse<Any>
}