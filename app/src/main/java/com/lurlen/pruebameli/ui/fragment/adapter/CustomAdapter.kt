package com.lurlen.pruebameli.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lurlen.pruebameli.R
import com.lurlen.pruebameli.data.api.Producto
import com.lurlen.pruebameli.databinding.ResultadoBusquedaItemBinding


class CustomAdapter(
    private val onClick: (Producto) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var productos: List<Producto> = emptyList()

    fun setNuevosProductos(nuevos: List<Producto>) {
        productos = nuevos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ResultadoBusquedaItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.onBind(productos[position])
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    class ViewHolder(
        private val binding: ResultadoBusquedaItemBinding,
        private val onClick: (Producto) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Producto) {
            binding.TVTitulo.text = item.title
            binding.TVPrecioValor.text = item.price.toString()
            val drawable = ContextCompat.getDrawable(itemView.context, R.drawable.img_mercado_libre)
            binding.IVImageProducto.setImageDrawable(drawable)
            itemView.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }
}
