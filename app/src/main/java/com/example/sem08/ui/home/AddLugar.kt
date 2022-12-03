package com.example.sem08.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.sem08.R
import com.example.sem08.databinding.FragmentAgregarLugarBinding
import com.example.sem08.model.Lugar
import com.example.sem08.viewmodel.HomeViewModel


class AddLugar : Fragment() {

    private var _binding: FragmentAgregarLugarBinding?= null
    private val binding get()= _binding!!
    private lateinit var lugarViewModel: HomeViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lugarViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentAgregarLugarBinding.inflate(inflater,container,false)
        binding.btAgregarPlatillo.setOnClickListener{agregarLugar()}



        // Inflate the layout for this fragment
        return binding.root
    }

    private fun agregarLugar() {
        val nombre = binding.etNombre.text.toString()
        val descripcion = binding.etDescripcion.text.toString()
        val precio = binding.etPrecio.text.toString()
        //val web = binding.etWeb.text.toString()

        if (nombre.isNotEmpty()){
            val lugar = Lugar("",nombre,descripcion,precio)
            //Proceso de agregar BD
            lugarViewModel.saveLugar(lugar)
            Toast.makeText(requireContext(),getString(R.string.msg_exitoPlatillo),Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_error),Toast.LENGTH_LONG).show()
        }
    }


}