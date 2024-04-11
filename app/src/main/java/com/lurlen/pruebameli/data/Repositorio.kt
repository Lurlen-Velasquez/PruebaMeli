package com.lurlen.pruebameli.data

import com.lurlen.pruebameli.data.api.ApiService
import com.lurlen.pruebameli.data.api.ResultadoBusqueda
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repositorio {

    private val baseUrl = "https://api.mercadolibre.com/"
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val okHttp = OkHttpClient.Builder().build()
    private val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttp).build()
    private val api: ApiService = retrofit.create(ApiService::class.java)

    suspend fun buscar(busqueda: String): ResultadoBusqueda? {
        return api.buscar(busqueda).body()
    }
}