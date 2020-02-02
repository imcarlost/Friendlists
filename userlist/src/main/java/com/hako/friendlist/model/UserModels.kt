package com.hako.friendlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val realName: String,
    @SerializedName("username") val userName: String,
    @SerializedName("email") val email: String,
    @SerializedName("amount") val balance: Address,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String,
    @SerializedName("company") val company: Company
) : Parcelable {

    @Parcelize
    data class Address(
        @SerializedName("street") val street: String,
        @SerializedName("suite") val realName: String,
        @SerializedName("city") val userName: String,
        @SerializedName("zipcode") val email: String,
        @SerializedName("geo") val geoLocation: GeoLocation
    ) : Parcelable {

        @Parcelize
        data class GeoLocation(
            @SerializedName("lat") val latitude: String,
            @SerializedName("lng") val longitude: String
        ) : Parcelable
    }

    @Parcelize
    data class Company(
        @SerializedName("name") val name: String,
        @SerializedName("catchPhrase") val catchPhrase: String,
        @SerializedName("bs") val keywords: String
    ) : Parcelable {
        fun getKeywordList() = keywords.split(" ")
    }
}

