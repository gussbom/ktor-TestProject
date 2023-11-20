package com.gusbom.plugins

import com.gusbom.authentication.JWTService
import com.gusbom.repositories.UserRepository
import com.gusbom.routes.customerRouting
import com.gusbom.routes.userRouting
import com.gusbom.services.implementation.UserServiceImpl
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val userRepository = UserRepository()
    val jwt = JWTService()
    //val hashFunction = {s:String -> hashFunction(s)
    routing {
        val userService = UserServiceImpl(userRepository)
        customerRouting()
        userRouting(userService)
    }
    }

