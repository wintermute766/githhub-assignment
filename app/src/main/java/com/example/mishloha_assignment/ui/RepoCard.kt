package com.example.mishloha_assignment.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mishloha_assignment.R
import com.example.mishloha_assignment.data.model.Item

@Composable
fun RepoCard(
    response: Item?
) {
    val name = response?.name
    val description = response?.description
    val avatarUrl = response?.owners?.avatarUrl
    val language = response?.language
    val forks = response?.forksCount
    val createdAt = response?.createdAt
    val htmlUrl = response?.htmlUrl

    val openDetails = remember { mutableStateOf(false) }

    if (openDetails.value) {
        MinimalDialog(
            openDetails,
            avatarUrl,
            name,
            description,
            language,
            forks,
            createdAt,
            htmlUrl
        ) {
            openDetails.value = false
        }
    }
    showCard(openDetails, avatarUrl, name, description)
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun showCard(
    openDetails: MutableState<Boolean>,
    avatarUrl: String?,
    name: String?,
    description: String?,
    language: String? = null,
    forks: Int? = null,
    createdAt: String? = null,
    htmlUrl: String? = null,
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        onClick = {
            openDetails.value = true
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.padding(8.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(avatarUrl ?: "-")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(20.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = name ?: "",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = description ?: "",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                language?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                forks?.let {
                    Text(
                        text = it.toString(),
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                createdAt?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                htmlUrl?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MinimalDialog(
    openDetails: MutableState<Boolean>,
    avatarUrl: String?,
    name: String?,
    description: String?,
    language: String?,
    forks: Int?,
    createdAt: String?,
    htmlUrl: String?,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        showCard(
            openDetails,
            avatarUrl,
            name,
            description,
            language,
            forks,
            createdAt,
            htmlUrl
        )
    }
}