package com.example.mishloha_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val htmlUrl: String
) : Parcelable
