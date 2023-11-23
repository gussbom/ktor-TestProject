package com.gusbom.plugins

import com.gusbom.models.tables.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI

object DbConnectivity{
    fun init(){
        Database.connect(hikari())

        transaction{
            SchemaUtils.create(Users)
        }
    }

    private fun hikari(): HikariDataSource{
        val config = HikariConfig()
        config.jdbcUrl  = "jdbc:postgresql://ec2-44-213-151-75.compute-1.amazonaws.com:5432/ddm2vd6ef5c251?sslmode=require"
        config.driverClassName = dotenv()["JDBC_DRIVER"]
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.username = dotenv()["DATABASE_USERNAME"]
        config.password = dotenv()["DATABASE_PASSWORD"]
        config.validate()
        return HikariDataSource(config)
    }


    suspend fun<T> dbQuery(block: ()->T): T=
        withContext(Dispatchers.IO){
            transaction { block() }
        }
}
