package com.example.retromvvm.repository


import android.widget.Toast
import com.example.retromvvm.model.domain.Wallpaper
import com.example.retromvvm.model.networking.API
import com.example.retromvvm.model.networking.RetroService
import retrofit2.Response


class MainRepository {

      fun retroService(): RetroService = API.apiService




}