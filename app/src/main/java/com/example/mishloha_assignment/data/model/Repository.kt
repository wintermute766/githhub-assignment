package com.example.mishloha_assignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Repository {
    @SerializedName("total_count")
    @Expose
    var totalCount : Int = 0

    @SerializedName("incomplete_results")
    @Expose
    var isinCompleteResults : Boolean = false
}