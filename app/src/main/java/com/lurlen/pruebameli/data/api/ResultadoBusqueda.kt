package com.lurlen.pruebameli.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultadoBusqueda(
    @Json(name = "site_id") val siteId: String? = null,
    @Json(name = "query") val query: String? = null,
    @Json(name = "paging") val paging: Paging? = Paging(),
    @Json(name = "results") val results: List<Producto>? = null
)
