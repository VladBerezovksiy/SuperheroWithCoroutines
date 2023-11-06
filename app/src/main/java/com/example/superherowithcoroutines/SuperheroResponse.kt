package com.example.superherowithcoroutines


data class SuperheroResponse(val name: String, val images: Images, val work: Works)

data class Works(val occupation: String)

data class Images(val xs: String)