package com.example.assignment.data.repository

import com.example.assignment.data.api.GithubEndpoint
import com.example.assignment.data.model.Repository
import javax.inject.Inject

class GithubRepository @Inject constructor(private val githubEndpoint: GithubEndpoint) {
    suspend fun getRepos(page: Int, limit: Int, map: Map<String, String>): Result<Repository> {
        return try {
            val repos = githubEndpoint.getRepos(page, limit, map)
            Result.success(repos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
