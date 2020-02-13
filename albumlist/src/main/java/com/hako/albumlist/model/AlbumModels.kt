package com.hako.albumlist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String
) : Parcelable

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

data class Album(
    val id: Int,
    val userId: Int,
    val title: String
)

fun AlbumRemote.toAlbumEntity() = AlbumEntity(this.id, this.userId, this.title)

fun AlbumEntity.toAlbumViewable() = Album(this.id, this.userId, this.title)

