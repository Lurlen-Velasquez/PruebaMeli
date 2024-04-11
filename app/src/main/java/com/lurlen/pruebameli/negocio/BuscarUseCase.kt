package com.lurlen.pruebameli.negocio

import com.lurlen.pruebameli.data.Repositorio
import com.lurlen.pruebameli.data.api.Producto

class BuscarUseCase {

    private val repositorio: Repositorio = Repositorio()
    suspend fun buscar(busqueda: String): List<Producto> {
        val resultadoBusqueda = repositorio.buscar(busqueda)
        val productos = if (resultadoBusqueda?.results != null) {
            resultadoBusqueda.results
        } else {
            emptyList()
        }
        return productos
    }
}