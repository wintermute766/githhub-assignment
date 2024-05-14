package com.example.mishloha_assignment.data.api

import com.example.mishloha_assignment.data.model.Repository
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface GithubEndpoint {
    @GET("search/repositories")
    suspend fun getRepos(
        @Query("page") page: Int,
        @Query("per_page") limit: Int,
        @QueryMap map: Map<String, String>
    ): Repository
}