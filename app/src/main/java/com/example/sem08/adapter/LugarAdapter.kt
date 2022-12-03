package com.example.sem08.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sem08.databinding.FragmentLugarFilaBinding
import com.example.sem08.model.Lugar
import com.example.sem08.ui.home.HomeFragmentDirections
import com.example.sem08.ui.home.UpdateLugarFragment
import com.example.sem08.ui.home.UpdateLugarFragmentDirections

class LugarAdapter: RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {

    private var listaPlatillos = emptyList<Lugar>()

    inner class LugarViewHolder( private val itemBinding: FragmentLugarFilaBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun dibujar(platillo: Lugar){
            itemBinding.tvNombre.text = platillo.nombre
            itemBinding.tvDescripcion.text = platillo.descripcion
            itemBinding.tvPrecio.text = platillo.precio

            //Evento edit
            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections
                    .actionNavHomeToUpdateLugarFragment(platillo)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    //Función para llenar la lista
    fun setPLatillos(platillos : List<Lugar>){
        listaPlatillos = platillos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding = FragmentLugarFilaBinding
            .inflate(LayoutInflater.from(parent.context)
            ,parent,false)

        return LugarViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val platillo = listaPlatillos[position]
        holder.dibujar(platillo)
    }

    override fun getItemCount(): Int {
        return listaPlatillos.size
    }


}