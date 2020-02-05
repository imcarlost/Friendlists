package com.hako.photolist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hako.base.domain.database.entities.PhotoEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @SerializedName("id") val id: Int,
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val photoUrl: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
) : Parcelable

data class PhotoViewable(
    val id: Int,
    val albumId: Int,
    val title: String,
    val photoUrl: String
)

fun Photo.toPhotoEntity() = PhotoEntity(this.id, this.albumId, this.title, this.photoUrl, this.thumbnailUrl)

fun PhotoEntity.toPhotoViewable() = PhotoViewable(this.id, this.albumId, this.title, this.photoUrl)

