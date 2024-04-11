package com.lurlen.pruebameli.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lurlen.pruebameli.data.api.Producto
import com.lurlen.pruebameli.negocio.BuscarUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BusquedaViewModel : ViewModel() {

    val busquedaLista: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val verDetalle: MutableSharedFlow<Boolean> = MutableSharedFlow()

    val estadoBusqueda: MutableStateFlow<List<Producto>> = MutableStateFlow(emptyList())
    val detalle: MutableStateFlow<Producto?> = MutableStateFlow(null)
    fun buscar(busqueda: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val buscarUseCase = BuscarUseCase()
            val resultados = buscarUseCase.buscar(busqueda)
            busquedaLista.emit(true)
            estadoBusqueda.emit(resultados)
        }
    }

    fun setProductoDetalle(producto: Producto) {
        viewModelScope.launch {
            detalle.emit(producto)
            verDetalle.emit(true)
        }
    }
}