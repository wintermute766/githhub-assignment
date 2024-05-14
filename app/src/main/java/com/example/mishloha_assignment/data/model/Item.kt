package com.example.mishloha_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("clone_url") val cloneUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("language") val language: String,
    @SerializedName("license") val licenses: License,
    @SerializedName("owner") val owners: Owner,
    @SerializedName("stargazers_count") val starCount: Int
) : Parcelable