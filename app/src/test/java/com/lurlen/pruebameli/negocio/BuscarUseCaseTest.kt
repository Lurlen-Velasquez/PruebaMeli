package com.lurlen.pruebameli.negocio

import com.lurlen.pruebameli.data.Repositorio
import com.lurlen.pruebameli.data.api.Producto
import com.lurlen.pruebameli.data.api.ResultadoBusqueda
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class BuscarUseCaseTest {

    private val repositorio: Repositorio = mockk(relaxed = true)
    private val sut = BuscarUseCase(repositorio)

    @Test
    fun retornarListaVacia() = runTest {
        val resultadoBusqueda: ResultadoBusqueda = mockk(relaxed = true)
        coEvery { repositorio.buscar(any()) } returns resultadoBusqueda
        val resultado = sut.buscar("asdf")
        val esperado = emptyList<Producto>()
        assertEquals(esperado, resultado)
    }

    @Test
    fun retornarListaVaciaSiHayError() = runTest {
        coEvery { repositorio.buscar(any()) } throws NullPointerException()
        val resultado = sut.buscar("asdf")
        val esperado = emptyList<Producto>()
        assertEquals(esperado, resultado)
    }

    @Test
    fun retornarListaDeProductos() = runTest {
        val producto1 = Producto(id = "1")
        val producto2 = Producto(id = "2")
        val productos = listOf(producto1, producto2)
        val resultadoBusqueda: ResultadoBusqueda = mockk(relaxed = true)
        coEvery { repositorio.buscar(any()) } returns resultadoBusqueda
        coEvery { resultadoBusqueda.results } returns productos

        val resultado = sut.buscar("asdf")

        assertEquals(productos, resultado)
    }
}