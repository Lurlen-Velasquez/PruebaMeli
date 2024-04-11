package com.lurlen.pruebameli.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lurlen.pruebameli.databinding.FragmentDetalleProductoBinding
import com.lurlen.pruebameli.ui.viewmodel.BusquedaViewModel

class DetalleProductoFragment : Fragment() {

    private lateinit var binding: FragmentDetalleProductoBinding
    private val viewModel: BusquedaViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalleProductoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agregarFlechaToolBar()
        observeDetalleProducto()
    }

    private fun agregarFlechaToolBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun observeDetalleProducto() {

    }
}