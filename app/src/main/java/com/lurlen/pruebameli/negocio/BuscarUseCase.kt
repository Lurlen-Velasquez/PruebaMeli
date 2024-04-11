package com.lurlen.pruebameli.negocio

import com.lurlen.pruebameli.data.api.Producto

class BuscarUseCase {
    suspend fun buscar(busqueda: String): List<Producto> {
        return listOf(
            Producto(
                id = "id1",
                title = "titulo 1"
            ),
            Producto(
                id = "id2",
                title = "titulo 2"
            )
        )
    }
}