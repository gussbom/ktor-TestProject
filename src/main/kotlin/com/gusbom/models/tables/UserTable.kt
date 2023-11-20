package com.gusbom.models.tables

import org.jetbrains.exposed.sql.Table
object Users : Table() {
    private val id = integer("id").autoIncrement()
    val username = varchar("username", 128).uniqueIndex()
    val password = varchar("password", 128)
    val email = varchar("email", 255).uniqueIndex()
    override val primaryKey = PrimaryKey(id)
}