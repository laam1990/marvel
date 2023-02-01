package com.example.marvel.ui.character.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvel.data.model.BaseResponseData
import com.example.marvel.ui.character.viewmodel.CharacterViewModel
import com.example.marvel.ui.theme.MarvelTheme
import androidx.compose.runtime.getValue
import com.example.marvel.data.util.Resource
import com.example.marvel.ui.character.CharacterListItem
import com.example.marvel.ui.character.SearchView
import com.example.marvel.ui.character.getPreviewCharacters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel
        setContent {
            MarvelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                    ) { content ->
                        Column(modifier = Modifier.padding(content)) {
                            SearchViewPreview(viewModel)
                            InitObservers()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun InitObservers() {
        val status by viewModel.characterListLiveData.observeAsState()
        when (status) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                (status as Resource.Success<BaseResponseData>).data?.let {
                    it.data?.characters?.let { characters ->
                        CharacterListItem(characters, onItemClick = {
                            /*TODO go to detail*/
                        })
                    }
                }
            }
            else -> {
            /*TODO Error*/
            }
        }
    }
}

@Composable
fun SearchViewPreview(viewModel: CharacterViewModel) {
    SearchView(onQueryChanged = {
        viewModel.getCharacter(it)
    })
}

@Preview(showBackground = true, device = "id:pixel_6_pro")
@Composable
fun DefaultPreview() {
    MarvelTheme {
        Scaffold { content ->
            Column(modifier = Modifier.padding(content)) {
                SearchView(onQueryChanged = {})
                CharacterListItem(listCharacter = getPreviewCharacters(), onItemClick = {})
            }
        }
    }
}