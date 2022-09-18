package com.example.retromvvm.repository

import com.example.retromvvm.model.domain.Wallpaper
import com.example.retromvvm.model.networking.API
import com.example.retromvvm.model.networking.RetroService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository {
    fun retroService(): RetroService = API.apiService

}