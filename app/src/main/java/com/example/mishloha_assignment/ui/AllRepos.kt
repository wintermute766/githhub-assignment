package com.example.mishloha_assignment.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    LaunchedEffect(Unit) {
        viewModel.loadReposDay()
    }

    val response = viewModel.repoResponse.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(stringResource(R.string.title)) }
            )
            dropDownMenu(viewModel)
        }
    ) { it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(response.itemCount) {
                RepoCard(response[it])
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
}

@Composable
fun dropDownMenu(viewModel: GithubReposViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                content = { Text("Day") },
                onClick = {
                    viewModel.loadReposDay()
                    expanded = false
                }
            )
            DropdownMenuItem(
                content = { Text("Week") },
                onClick = {
                    viewModel.loadReposWeek()
                    expanded = false
                }
            )
            DropdownMenuItem(
                content = { Text("Month") },
                onClick = {
                    viewModel.loadReposMonth()
                    expanded = false
                }
            )
        }
    }
}

//TODO
// Details: labels, date format, link
// Favorites
// The number of stars
// README.md
// Search?