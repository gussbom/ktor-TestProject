package com.gusbom.routes

import com.gusbom.dto.requests.RegistrationRequest
import com.gusbom.dto.responses.GenericResponse
import com.gusbom.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val API_VERSION = "/v1/"
const val USERS = "$API_VERSION/users"
fun Route.userRouting(
    userService: UserService
){

        route(USERS) {
            post("/register") {
                val newRegistration = try {
                    call.receive<RegistrationRequest>()
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, GenericResponse(false, "Missing fields", null))
                    return@post
                }
                val regResponse = userService.registerUser(newRegistration)
                if(regResponse.success){
                    call.respond(HttpStatusCode.Created, regResponse)
                }else{
                    call.respond(HttpStatusCode.Conflict, regResponse)
                }


            }
        }
    }