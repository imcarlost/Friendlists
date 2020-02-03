package com.hako.base.domain.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = AlbumEntity.TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
data class AlbumEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String
) {
    companion object {
        const val TABLE_NAME = "albums"
    }
}