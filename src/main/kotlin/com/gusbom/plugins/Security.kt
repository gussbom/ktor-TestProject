package com.gusbom.plugins


import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.application.*

//fun Application.configureFirebaseAuth() {
//    install(Authentication) {
//        firebase {
//            validate {
//                // TODO look up user profile from DB
//                User(it.uid, it.name.orEmpty())
//            }
//        }
//    }
//}