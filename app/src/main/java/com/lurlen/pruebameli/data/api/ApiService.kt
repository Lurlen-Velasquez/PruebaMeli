package com.lurlen.pruebameli.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("sites/MLA/search")
    suspend fun buscar(@Query("q") query: String): Response<ResultadoBusqueda>
}