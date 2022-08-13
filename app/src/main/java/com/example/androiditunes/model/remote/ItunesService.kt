package com.example.androiditunes.model.remote

import com.example.androiditunes.model.ItunesDetails
import retrofit2.Call
import retrofit2.http.GET

interface ItunesService {

    @GET(ROCK_ITUNES)
    fun queryRockItunes() : Call<ItunesDetails>


    @GET(CLASSIC_ITUNES)
    fun queryClassicItunes(): Call<ItunesDetails>

    @GET(POP_ITUNES)
    fun queryPopItunes() :  Call<ItunesDetails>

}