package com.example.sem08.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sem08.data.LugarDao
import com.example.sem08.model.Lugar

class LugarRepository(private val lugarDao: LugarDao) {
     fun saveLugar(platillo: Lugar){
        lugarDao.savePlatillo(platillo)
    }

     fun deleteLugar(platillo: Lugar){
        lugarDao.deletePlatillo(platillo)
    }

    val getLugar: MutableLiveData<List<Lugar>> = lugarDao.obtenerPlatillos()

}