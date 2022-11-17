package com.khater.retromvvm.repository


import com.khater.retromvvm.model.networking.API
import com.khater.retromvvm.model.networking.RetroService

class MainRepository {
    fun retroService(): RetroService = API.apiService
}