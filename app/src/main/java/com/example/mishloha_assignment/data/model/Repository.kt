package com.example.mishloha_assignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository (
    @SerializedName("total_count")
    @Expose
    var totalCount : Int = 0,

    @SerializedName("incomplete_results")
    @Expose
    var isInCompleteResults : Boolean = false,

    @SerializedName("items")
    @Expose
    var items : List<Item> = emptyList()
) : Parcelable