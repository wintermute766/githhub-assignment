package com.example.assignment.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.assignment.R
import com.example.assignment.data.model.Item
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun RepoCard(
    response: Item?
) {
    val name = response?.name
    val description = response?.description
    val avatarUrl = response?.owners?.avatarUrl
    val language = response?.language
    val forks = response?.forksCount
    val stars = response?.starCount

    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    val createdAt = simpleDateFormat.parse(response?.createdAt ?: "")?.toString()

    val htmlUrl = response?.htmlUrl

    val openDetails = remember { mutableStateOf(false) }

    if (openDetails.value) {
        DetailsDialog(
            openDetails,
            avatarUrl,
            name,
            description,
            stars,
            language,
            forks,
            createdAt,
            htmlUrl
        ) {
            openDetails.value = false
        }
    }
    showCard(openDetails, avatarUrl, name, description, stars)
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun showCard(
    openDetails: MutableState<Boolean>,
    avatarUrl: String?,
    name: String?,
    description: String?,
    stars: Int?,
    language: String? = null,
    forks: Int? = null,
    createdAt: String? = null,
    htmlUrl: String? = null,
) {
    val context = LocalContext.current

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
                Row {
                    Text(
                        text = name ?: "",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .wrapContentSize(),
                        color = MaterialTheme.colors.onSurface,
                    )
                    Text(
                        text = "   ★ $stars",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier
                            .wrapContentSize(),
                        color = MaterialTheme.colors.onSurface,
                    )
                }
                Text(
                    text = description ?: "",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                language?.let {
                    Text(
                        text = "Language: $it",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                forks?.let {
                    Text(
                        text = "Forks: $it",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                createdAt?.let {
                    Text(
                        text = "Created at: $it",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                val annotatedString = buildAnnotatedString {
                    pushStringAnnotation(tag = "url", annotation = htmlUrl ?: "")
                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                        append(htmlUrl)
                    }
                    pop()
                }
                htmlUrl?.let {
                    Text(
                        text = annotatedString,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse(htmlUrl)
                                }
                                try {
                                    context.startActivity(intent)
                                } catch (_: ActivityNotFoundException) {
                                    // Do nothing
                                }
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun DetailsDialog(
    openDetails: MutableState<Boolean>,
    avatarUrl: String?,
    name: String?,
    description: String?,
    stars: Int?,
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
            stars,
            language,
            forks,
            createdAt,
            htmlUrl
        )
    }
}