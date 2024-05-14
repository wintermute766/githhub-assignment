package com.example.mishloha_assignment.data.api

import com.example.mishloha_assignment.data.model.Repository
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubEndpoint {
    @GET("search/repositories")
    suspend fun getRepos(@QueryMap map: Map<String, String>): Repository
}