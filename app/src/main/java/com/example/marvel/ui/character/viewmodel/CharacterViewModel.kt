package com.example.marvel.ui.character.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.model.BaseResponseData
import com.example.marvel.data.util.Resource
import com.example.marvel.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _characterListLiveData = MutableLiveData<Resource<BaseResponseData>>()
    val characterListLiveData: LiveData<Resource<BaseResponseData>>
        get() = _characterListLiveData

    fun getCharacter(name: String) {
        viewModelScope.launch {
            repository.getCharacters(name).collect {
                _characterListLiveData.value = it
            }
        }
    }
}