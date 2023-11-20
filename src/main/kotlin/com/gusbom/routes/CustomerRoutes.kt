package com.gusbom.routes

import com.gusbom.models.Customer
import com.gusbom.models.customers
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting(){
    route("/customer"){

        get{
            if(customers.isNotEmpty()){
                call.respond(customers)
            }
            else{
                call.respondText("Customer Not Found", status = HttpStatusCode.OK)
            }
        }

        get("{id?}"){
            val id = call.parameters["id"] ?: call.respondText ("Missing id", status = HttpStatusCode.BadRequest )
            val customer = customers.find{it.id == id} ?: return@get call.respondText("Customer id $id does not exist", status = HttpStatusCode.NotFound)
            call.respond(customer)
        }

        post(""){
            val customer = call.receive<Customer>()
            customers.add(customer)
            call.respondText ("New Customer Created", status = HttpStatusCode.Created)
        }

        delete("{id?}"){
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if(customers.removeIf{it.id==id})(
                call.respondText ("Customer Deleted", status = HttpStatusCode.Accepted)
            )
            else{
                call.respondText("Customer Id $id not found", status = HttpStatusCode.BadRequest)
            }
        }
    }
}