package com.example.superherowithcoroutines

import retrofit2.Response
import retrofit2.Retrofit

class Repository(client: Retrofit) {

    private val apiInterface = client.create(ApiInterface::class.java)

    suspend fun getSuperhero(): Response<List<SuperheroResponse>> = apiInterface.getSuperhero()

}