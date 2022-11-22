package com.example.sem08.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sem08.R
import com.example.sem08.databinding.FragmentUpdateLugarBinding
import com.example.sem08.model.Lugar
import com.example.sem08.viewmodel.HomeViewModel

class UpdateLugarFragment : Fragment() {

    //Variable que nos va a recuperar argumentos
    private val args by navArgs<UpdateLugarFragmentArgs>()

    //objeto del xml
    private var _binding: FragmentUpdateLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentUpdateLugarBinding.inflate(inflater,container,false)

        //Cargar los valores edit
        binding.etNombre.setText(args.lugar.nombre)
        binding.etTelefono.setText(args.lugar.telefono)
        binding.etEmail.setText(args.lugar.correo)
        binding.etWeb.setText(args.lugar.web)

        binding.btUpdateLugar.setOnClickListener{updateLugar()}

        //Binging del botón eliminar lugar, tarea semana 10
        binding.btDeleteLugar.setOnClickListener { deleteLugar() }

        // Inflate the layout for this fragment
        return binding.root
    }

    //Función para eliminar el lugar, tarea semana 10
    private fun deleteLugar() {
        val nombre = binding.etNombre.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val correo = binding.etEmail.text.toString()
        val web = binding.etWeb.text.toString()

            val lugar = Lugar(args.lugar.id,nombre,correo,web,telefono)
            homeViewModel.deleteLugar(lugar)
            Toast.makeText(requireContext(),getString(R.string.msg_lugarBorrado),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateLugarFragment_to_nav_home)

    }

    private fun updateLugar() {
        val nombre = binding.etNombre.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val correo = binding.etEmail.text.toString()
        val web = binding.etWeb.text.toString()
        if (nombre.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else if (correo.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else{
            val lugar = Lugar(args.lugar.id,nombre,correo,web,telefono)
            homeViewModel.saveLugar(lugar)
            Toast.makeText(requireContext(),getString(R.string.msg_lugar_updated),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateLugarFragment_to_nav_home)
        }
    }


}