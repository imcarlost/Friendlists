package com.hako.photolist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val photoUrl: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
) : Parcelable

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

data class Photo(
    val id: Int,
    val albumId: Int,
    val title: String,
    val photoUrl: String
)

fun PhotoRemote.toPhotoEntity() = PhotoEntity(this.id, this.albumId, this.title, this.photoUrl, this.thumbnailUrl)

fun PhotoEntity.toPhotoViewable() = Photo(this.id, this.albumId, this.title, this.photoUrl)

