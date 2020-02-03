package com.hako.base.domain.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val realName: String,
    val userName: String,
    val email: String,
    val phone: String,
    val website: String
) {
    companion object {
        const val TABLE_NAME = "users"
    }
}