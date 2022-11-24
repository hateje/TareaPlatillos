package com.example.sem08.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sem08.data.LugarDao
import com.example.sem08.model.Lugar

class LugarRepository(private val lugarDao: LugarDao) {
     fun saveLugar(lugar: Lugar){
        lugarDao.saveLugar(lugar)
    }

     fun deleteLugar(lugar: Lugar){
        lugarDao.deleteLugar(lugar)
    }

    val getLugar: MutableLiveData<List<Lugar>> = lugarDao.obtenerLugares()

}