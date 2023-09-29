package com.example.superherowithcoroutines

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("superhero-api/api/all.json")
    suspend fun getSuperhero(): Response<List<SuperheroResponse>>

}