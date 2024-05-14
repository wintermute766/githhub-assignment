package com.example.mishloha_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository (
    @SerializedName("total_count") val totalCount: Int = 0,
    @SerializedName("incomplete_results") val isInCompleteResults: Boolean = false,
    @SerializedName("items") val items: List<Item> = emptyList()
) : Parcelable