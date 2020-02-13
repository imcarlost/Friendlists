package com.hako.userlist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val realName: String,
    @SerializedName("username") val userName: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String
) : Parcelable

@Entity(tableName = UserEntity.TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val realName: String,
    val userName: String,
    val email: String,
    val phone: String,
    val website: String,
    val isFavorite: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "users"
    }
}

data class User(
    val id: Int,
    val realName: String,
    val userName: String,
    var isFavorite: Boolean
)

fun UserRemote.toUserEntity() = UserEntity(this.id, this.realName, this.userName, this.email, this.phone, this.website)

fun UserEntity.toUserViewable() = User(this.id, this.realName, this.userName, this.isFavorite)

