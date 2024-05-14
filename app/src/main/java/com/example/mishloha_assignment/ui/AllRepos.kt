package com.example.mishloha_assignment.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mishloha_assignment.R
import com.example.mishloha_assignment.viewmodel.GithubReposViewModel

@Composable
fun AllRepos(
    viewModel: GithubReposViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.loadRepos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(stringResource(R.string.title)) }
            )
        }
    ) { it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(viewModel.uiState.repos?.items ?: emptyList()) { repo ->
                RepoCard(repo.name, repo.description)
            }
        }
    }
}