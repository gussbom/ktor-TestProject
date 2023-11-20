package com.gusbom.plugins

import com.gusbom.models.tables.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DbConnectivity{
    fun init(){
        Database.connect(hikari())

        transaction{
            SchemaUtils.create(Users)
        }
    }

    private fun hikari(): HikariDataSource{
        val config = HikariConfig()
        config.jdbcUrl  = "jdbc:postgresql:testProject_db?user\\=postgres&password\\=1234567890"//System.getenv("DATABASE_URL")
        config.driverClassName = "org.postgresql.Driver"//System.getenv("JDBC_DRIVER")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun<T> dbQuery(block: ()->T): T=
        withContext(Dispatchers.IO){
            transaction { block() }
        }
}
