package com.example.mishloha_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("html_url")
    @Expose
    var html_url: String,

    @SerializedName("clone_url")
    @Expose
    var clone_url: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("language")
    @Expose
    var language: String,

    @SerializedName("license")
    @Expose
    var licenses: License,

    @SerializedName("owner")
    @Expose
    var owners: Owner,

    @SerializedName("stargazers_count")
    @Expose
    var star_count: Int
) : Parcelable