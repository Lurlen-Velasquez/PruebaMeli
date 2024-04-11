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
import com.lurlen.pruebameli.databinding.FragmentBusquedaBinding
import com.lurlen.pruebameli.ui.viewmodel.BusquedaViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class BusquedaFragment : Fragment() {

    private lateinit var binding: FragmentBusquedaBinding
    private val viewModel: BusquedaViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBusquedaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removerFlechaToolBar()
        setUpButton()
        escucharResultadoBusqueda()
    }

    private fun removerFlechaToolBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun setUpButton() {
        binding.BBuscar.setOnClickListener {
            val busqueda = binding.ETBusqueda.text.toString()
            viewModel.buscar(busqueda)
        }
    }

    private fun escucharResultadoBusqueda() {
        lifecycleScope.launch {
            viewModel.busquedaLista.distinctUntilChanged().collect {
                reemplazarFragment()
            }
        }
    }

    private fun reemplazarFragment() {
        parentFragmentManager.commit {
            replace<ResultadoBusquedaFragment>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}