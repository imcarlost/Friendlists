package com.hako.albumlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hako.base.domain.database.entities.AlbumEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String
) : Parcelable

data class AlbumViewable(
    val id: Int,
    val userId: Int,
    val title: String
)

fun Album.toAlbumEntity() = AlbumEntity(this.id, this.userId, this.title)

fun AlbumEntity.toAlbumViewable() = AlbumViewable(this.id, this.userId, this.title)

