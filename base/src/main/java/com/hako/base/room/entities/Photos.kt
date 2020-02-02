package com.hako.base.room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = PhotoEntity.TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
data class PhotoEntity(
    @PrimaryKey
    val id: Int,
    val albumId: Int,
    val title: String,
    val photoUrl: String,
    val thumbnailUrl: String
) {
    companion object {
        const val TABLE_NAME = "photos"
    }
}