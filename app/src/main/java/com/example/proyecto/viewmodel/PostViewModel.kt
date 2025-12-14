package com.example.proyecto.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    //Lista de posts
    val posts = MutableStateFlow(emptyList<com.example.proyecto.model.Post>())

    //Carga de post
    fun cargaPost(){
        viewModelScope.launch {
            val respuesta = RetrofitInstance.api.getPosts()
            posts.value = respuesta
        }
    }

}