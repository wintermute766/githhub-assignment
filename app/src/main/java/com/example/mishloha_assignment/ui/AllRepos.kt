package com.example.mishloha_assignment.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mishloha_assignment.R
import com.example.mishloha_assignment.viewmodel.GithubReposViewModel

@Composable
fun AllRepos(
    viewModel: GithubReposViewModel = hiltViewModel()
) {
    val response = viewModel.repoResponse.collectAsLazyPagingItems()

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
            items(response.itemCount) {
                RepoCard(
                    response[it]?.name,
                    response[it]?.description
                )
            }

            response.apply {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }

                    loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                        item {
                            Text(text = "Error")
                        }
                    }

                    loadState.refresh is LoadState.NotLoading -> {}
                }
            }
        }
    }

    //TODO
    // Avatar & Glide
    // Details
    // Favorites
    // Search?
}