package com.example.sem08.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
//import androidx.room.*
import com.example.sem08.model.Lugar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class LugarDao {
    //Firebase var
    private var codigoUsuario: String
    private var firestore:FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //Insert
     fun saveLugar(lugar: Lugar){
        val document: DocumentReference
        if (lugar.id.isEmpty()){
            //Proceso de agregar
            document = firestore.
                    collection("lugaresMiercoles").
                    document(codigoUsuario).
                    collection("misLugares").
                    document()
            lugar.id= document.id
        }
        else{
            //modificar lugar
            document = firestore.
            collection("lugaresMiercoles").
            document(codigoUsuario).
            collection("misLugares").
            document(lugar.id)
        }
        document.set(lugar) //el método set es el que hace el update o insert
            .addOnCompleteListener{
                Log.d("SaveLugar","Guardado con éxito")
            }
            .addOnCanceledListener {
                Log.e("SaveLugar","Error al guardar")
            }
    }

    //Delete

     fun deleteLugar(lugar: Lugar){
        if (lugar.id.isNotEmpty()){
            firestore.
                    collection("lugaresMiercoles").
                    document(codigoUsuario).
                    collection("misLugares").
                    document(lugar.id).delete().
                addOnCompleteListener{
                    Log.d("deleteLugar","Eliminado con éxito")
                }
                .addOnCanceledListener {
                    Log.e("deleteLugar","Error al eliminar")
                }
        }
    }

    //Obtener datos

    fun obtenerLugares(): MutableLiveData<List<Lugar>> {
        val listaLugares = MutableLiveData<List<Lugar>>()
        firestore.
        collection("lugaresMiercoles").
        document(codigoUsuario).
        collection("misLugares").
                addSnapshotListener{snapshot,e ->
                    if(e != null){
                        return@addSnapshotListener
                    }
                    if (snapshot !=null){
                        val lista = ArrayList<Lugar>()
                        val lugares = snapshot.documents
                        lugares.forEach{
                            val lugar = it.toObject(Lugar::class.java)
                            if (lugar != null){
                                lista.add(lugar)
                            }
                        }
                        listaLugares.value= lista
                    }
                }
        return listaLugares
    }

}