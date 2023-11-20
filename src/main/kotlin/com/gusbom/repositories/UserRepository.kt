package com.gusbom.repositories

import com.gusbom.models.User
import com.gusbom.models.tables.Users
import com.gusbom.plugins.DbConnectivity.dbQuery
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

@Serializable
class UserRepository {
    suspend fun saveUser(user: User){
        dbQuery{
            Users.insert { ut->
                ut[username] = user.username
                ut[password] = user.password
                ut[email] = user.email
            }
        }
    }
    suspend fun findUserByEmail(email:String) = dbQuery {
        Users.select{Users.email.eq(email)}
            .map { rowToUser(it) }
            .singleOrNull()
    }

    suspend fun findUserByUserName(username:String) = dbQuery {
        Users.select{Users.username.eq(username)}
            .map { rowToUser(it) }
            .singleOrNull()
    }

    private fun rowToUser(row: ResultRow?):User?{
        if(row==null){
            return null
        }
        return User(
            username = row[Users.username],
            password = row[Users.password],
            email = row[Users.email]
        )

    }
}