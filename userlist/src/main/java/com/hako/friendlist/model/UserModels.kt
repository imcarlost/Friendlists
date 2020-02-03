package com.hako.friendlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hako.base.domain.database.entities.UserEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val realName: String,
    @SerializedName("username") val userName: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String
) : Parcelable

data class UserViewable(
    val id: Int,
    val realName: String,
    val userName: String,
    var isFavorite: Boolean = false
)

fun User.toUserEntity() = UserEntity(this.id, this.realName, this.userName, this.email, this.phone, this.website)

fun UserEntity.toUserViewable() = UserViewable(this.id, this.realName, this.userName)

