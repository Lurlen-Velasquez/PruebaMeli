package com.lurlen.pruebameli.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.lurlen.pruebameli.R
import com.lurlen.pruebameli.data.api.Producto
import com.lurlen.pruebameli.databinding.FragmentResultadoBusquedaBinding
import com.lurlen.pruebameli.ui.fragment.adapter.CustomAdapter
import com.lurlen.pruebameli.ui.viewmodel.BusquedaViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ResultadoBusquedaFragment : Fragment() {

    private lateinit var binding: FragmentResultadoBusquedaBinding
    private val adapter: CustomAdapter = CustomAdapter({ onClick(it) })
    private val viewModel: BusquedaViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultadoBusquedaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agregarFlechaToolBar()
        setUpAdapter()
        observeResultadoBusqueda()
        observeVerDetalle()
    }

    private fun agregarFlechaToolBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpAdapter() {
        binding.recyclerView.adapter = adapter
    }

    private fun observeResultadoBusqueda() {
        lifecycleScope.launch {
            viewModel.estadoBusqueda.collect { productos ->
                adapter.setNuevosProductos(productos)
            }
        }
    }

    private fun observeVerDetalle() {
        lifecycleScope.launch {
            viewModel.verDetalle.distinctUntilChanged().collect {
                reemplazarFragment()
            }
        }
    }

    private fun reemplazarFragment() {
        parentFragmentManager.commit {
            replace<DetalleProductoFragment>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun onClick(producto: Producto) {
        viewModel.setProductoDetalle(producto)
    }
}