package com.example.mishloha_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @SerializedName("login")
    @Expose
    var login: String,

    @SerializedName("avatar_url")
    @Expose
    var avatar_url: String,

    @SerializedName("html_url")
    @Expose
    var html_url: String
) : Parcelable
