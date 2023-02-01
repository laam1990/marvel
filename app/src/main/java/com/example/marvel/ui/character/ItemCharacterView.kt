package com.example.marvel.ui.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.marvel.R
import com.example.marvel.data.model.Character
import com.example.marvel.data.model.Comics
import com.example.marvel.data.model.Events
import com.example.marvel.data.model.Series
import com.example.marvel.data.model.Stories
import com.example.marvel.data.model.Summary
import com.example.marvel.data.model.Thumbnail
import com.example.marvel.data.model.Url

@Composable
fun CharacterListItem(
    listCharacter: List<Character>,
    onItemClick: (Character) -> Unit
) {
    LazyVerticalGrid(
        userScrollEnabled = true,
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        items(listCharacter.size) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(4.dp)
            ) {
                Card(
                    backgroundColor = colorResource(id = R.color.white_marvel),
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(listCharacter[index])
                        },
                    elevation = 8.dp,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(listCharacter[index].thumbnail?.getCompleteUrl())
                                .crossfade(true)
                                .size(Size.ORIGINAL)
                                .build(),
                            contentDescription = "stringResource(R.string.description)",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .height(200.dp)
                        )
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = listCharacter[index].name ?: "",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )
                        Box(
                            Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        Icons.Default.Favorite,
                                        contentDescription = null,
                                        tint = colorResource(R.color.red_marvel)
                                    )
                                }

                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        Icons.Default.Share,
                                        contentDescription = null,
                                        tint = colorResource(R.color.red_marvel)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_6_pro")
@Composable
fun ShowCharacterItem() {
    CharacterListItem(listCharacter = PreviewCharactersProvider().values.toList(), onItemClick = {})
}

class PreviewCharactersProvider : PreviewParameterProvider<Character> {
    override val values: Sequence<Character> = sequenceOf(
        Character(
            id = 1011334,
            name = "3-D Man",
            description = "",
            modified = "",
            resourceURI = "",
            comics = getComics(),
            series = getSeries(),
            stories = getStories(),
            events = getEvents(),
            thumbnail = getPreviewThumbnail(),
            urls = getPreviewUrl()
        ),
        Character(
            id = 1011335,
            name = "3-D Man (Danny Rand X)",
            description = "",
            modified = "",
            resourceURI = "",
            comics = getComics(),
            series = getSeries(),
            stories = getStories(),
            events = getEvents(),
            thumbnail = getPreviewThumbnail(),
            urls = getPreviewUrl()
        )
    )
}

fun getPreviewCharacters(): List<Character> {
    return listOf(
        Character(
            id = 1011334,
            name = "3-D Man",
            description = "",
            modified = "",
            resourceURI = "",
            comics = getComics(),
            series = getSeries(),
            stories = getStories(),
            events = getEvents(),
            thumbnail = getPreviewThumbnail(),
            urls = getPreviewUrl()
        ),
        Character(
            id = 1011335,
            name = "3-D Man (Danny Rand X)",
            description = "",
            modified = "",
            resourceURI = "",
            comics = getComics(),
            series = getSeries(),
            stories = getStories(),
            events = getEvents(),
            thumbnail = getPreviewThumbnail(),
            urls = getPreviewUrl()
        )
    )
}

fun getPreviewComicSummary(): List<Summary> {
    val comicSummary =
        Summary(name = "Avengers: The Initiative (2007) #14", resourceURI = "", type = null)
    return listOf(comicSummary)
}

fun getPreviewSeriesSummary(): List<Summary> {
    val seriesSummary =
        Summary(name = "Avengers: The Initiative (2007 - 2010)", resourceURI = "", type = null)
    return listOf(seriesSummary)
}

fun getPreviewStoriesSummary(): List<Summary> {
    val storiesSummary = Summary(name = "Cover #19947", resourceURI = "", type = "Cover")
    return listOf(storiesSummary)
}

fun getPreviewEventsSummary(): List<Summary> {
    val eventsSummary = Summary(name = "Secret Invasion", resourceURI = "", type = null)
    return listOf(eventsSummary)
}

fun getPreviewUrl(): List<Url> {
    val url = Url(
        type = "comiclink",
        url = "http://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=883bea04c7196768d7e50351f8ac4a08"
    )
    return listOf(url)
}

fun getPreviewThumbnail(): Thumbnail {
    return Thumbnail(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
        extension = "jpg"
    )
}

fun getComics(): Comics {
    return Comics(12, "", getPreviewComicSummary(), 1)
}

fun getSeries(): Series {
    return Series(3, "", getPreviewSeriesSummary(), 1)
}

fun getStories(): Stories {
    return Stories(21, "", getPreviewStoriesSummary(), 1)
}

fun getEvents(): Events {
    return Events(1, "", getPreviewEventsSummary(), 1)
}
