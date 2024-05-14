package com.example.mishloha_assignment.data

import com.example.mishloha_assignment.data.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories?q=created:>`date -v-1m '+%Y-%m-%d'`&sort=stars&order=desc&per_page=10")
    fun getRepositories(@Query("page") page: Int): Call<Repository>
}