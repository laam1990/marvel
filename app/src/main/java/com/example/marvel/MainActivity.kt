package com.example.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.marvel.data.model.Character
import com.example.marvel.data.model.Comics
import com.example.marvel.data.model.Events
import com.example.marvel.data.model.Series
import com.example.marvel.data.model.Stories
import com.example.marvel.data.model.Summary
import com.example.marvel.data.model.Thumbnail
import com.example.marvel.data.model.Url
import com.example.marvel.ui.theme.MarvelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MarvelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {}
                    ) { content ->
                        Column(modifier = Modifier.padding(content)) {
                            SearchViewPreview()
                            CharacterListItemPreview()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { onQueryChanged ->
            state.value = onQueryChanged
            /*if (state.value.text.isNotEmpty()) {
                //
            }*/
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Clear Icon"
                )
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = { Text(text = "Busca tu héroe") },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
    )
}

@Composable
fun SearchViewPreview() {
    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    SearchView(state = textState)
}

@Composable
fun CharacterListItem(listCharacter: List<Character>, onItemClick: (Character) -> Unit) {
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
                    Column {
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
                                .padding(8.dp)
                                .fillMaxWidth(),
                            text = listCharacter[index].name ?: "",
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
fun CharacterListItemPreview() {
    val comicSummary =
        Summary(name = "Avengers: The Initiative (2007) #14", resourceURI = "", type = null)
    val listComicSummary = listOf(comicSummary)

    val seriesSummary =
        Summary(name = "Avengers: The Initiative (2007 - 2010)", resourceURI = "", type = null)
    val listSeriesSummary = listOf(seriesSummary)

    val storiesSummary = Summary(name = "Cover #19947", resourceURI = "", type = "Cover")
    val listStoriesSummary = listOf(storiesSummary)

    val eventsSummary = Summary(name = "Secret Invasion", resourceURI = "", type = null)
    val listEventsSummary = listOf(eventsSummary)

    val url = Url(
        type = "comiclink",
        url = "http://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=883bea04c7196768d7e50351f8ac4a08"
    )
    val urls = listOf(url)

    val thumbnail = Thumbnail(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
        extension = "jpg"
    )

    val comics = Comics(12, "", listComicSummary, 1)
    val series = Series(3, "", listSeriesSummary, 1)
    val stories = Stories(21, "", listStoriesSummary, 1)
    val events = Events(1, "", listEventsSummary, 1)

    val character = Character(
        id = 1011334,
        name = "3-D Man",
        description = "",
        modified = "",
        resourceURI = "",
        comics = comics,
        series = series,
        stories = stories,
        events = events,
        thumbnail = thumbnail,
        urls = urls
    )
    val listCharacter = listOf(character, character, character, character)
    CharacterListItem(listCharacter, onItemClick = {

    })
}

@Preview(showBackground = true, device = "id:pixel_6_pro")
@Composable
fun DefaultPreview() {
    MarvelTheme {
        SearchViewPreview()
    }
}
