package com.gusbom.authentication

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val hashKey = "chattooHashKey".toByteArray()
private val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")

fun hashFunction(password:String):String{
    val hmac = Mac.getInstance("HmacSHA1")
    hmac.init(hmacKey)
    return  hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}