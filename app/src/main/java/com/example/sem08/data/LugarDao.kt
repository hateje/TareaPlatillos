package com.example.sem08.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sem08.model.Lugar


@Dao
interface LugarDao {

    //Obtener datos
    @Query ("SELECT * FROM LUGAR")
    fun obtenerLugares(): LiveData<List<Lugar>>

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarLugar(lugar: Lugar)

    //Update
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun modificarLugar(lugar: Lugar)

    //Delete
    @Delete
    suspend fun eliminarLugar(lugar: Lugar)
}