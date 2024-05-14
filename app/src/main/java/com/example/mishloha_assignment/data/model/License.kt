package com.example.mishloha_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class License(
    @SerializedName("name") val name: String
) : Parcelable
