package com.gusbom.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.gusbom.models.User


class JWTService {

    //val dotenv: Dotenv = dotenv()

    private val issuer = "gusbom"
    private val jwtSecret =  "projectByGusbom" //System.getenv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC256(jwtSecret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(user: User):String{
        return JWT.create()
            .withSubject("chattooAuthentication")
            .withIssuer(issuer)
            .withClaim("email", user.email)
            .sign(algorithm)
    }
}
