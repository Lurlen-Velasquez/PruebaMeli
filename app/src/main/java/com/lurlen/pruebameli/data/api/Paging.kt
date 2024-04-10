package com.lurlen.pruebameli.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Paging(
    @Json(name = "total") val total: Int? = null,
    @Json(name = "primary_results") val primaryResults: Int? = null,
    @Json(name = "offset") val offset: Int? = null,
    @Json(name = "limit") val limit: Int? = null
)
