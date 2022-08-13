package com.example.androiditunes.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {

 val itunesApi: ItunesService by lazy {
    initRetrofit()
 }

 private fun initRetrofit(): ItunesService {
     return Retrofit.Builder()
         .addConverterFactory(GsonConverterFactory.create())
         .baseUrl(BASE_URL)
         .build()
         .create(ItunesService::class.java)
 }

}