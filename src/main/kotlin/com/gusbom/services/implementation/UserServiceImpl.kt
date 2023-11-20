package com.gusbom.services.implementation

import com.gusbom.authentication.JWTService
import com.gusbom.authentication.hashFunction
import com.gusbom.dto.requests.RegistrationRequest
import com.gusbom.dto.responses.GenericResponse
import com.gusbom.models.User
import com.gusbom.repositories.UserRepository
import com.gusbom.services.UserService
import io.ktor.http.*

//@Serializable
class UserServiceImpl(
    private val userRepository: UserRepository,
//    private val jwtService: JWTService,
//    private val hashFunction: (String) -> String
):UserService{
    override suspend fun registerUser(params: RegistrationRequest): GenericResponse<Any> {

//        val users = userRepository.findUserByEmail(params.email)

//        return if (user.? != null) {
//
//        } else {
            try {
                val user = User(
                    params.username,
                    hashFunction(params.password),
                    params.email
                )
                userRepository.saveUser(user)
                return GenericResponse(true, "Token Generated", "jwtService.generateToken(user)")
            } catch (e: Exception) {
                GenericResponse(false, e.message ?: "An error occurred", "null")
            }
            return GenericResponse(false, "Fatal Error", "jwtService")
        }
//    }
}