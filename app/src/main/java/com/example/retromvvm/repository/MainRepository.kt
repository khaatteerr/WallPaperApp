package com.example.retromvvm.repository



import com.example.retromvvm.model.networking.API
import com.example.retromvvm.model.networking.RetroService


class MainRepository {

      fun retroService(): RetroService = API.apiService




}