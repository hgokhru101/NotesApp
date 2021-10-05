package com.example.myapplication.Repository

import androidx.lifecycle.LiveData
import com.example.myapplication.database.DatabaseDoa
import com.example.myapplication.database.TableClass

class RepositoryClass(private val DatabaseDaoObject : DatabaseDoa) {
    val allData:LiveData<List<TableClass>> = DatabaseDaoObject.getAllData()
    suspend fun insert(TableClassObject:TableClass){
        DatabaseDaoObject.insert(TableClassObject)
    }
    suspend fun delete(TableClassObject: TableClass){
        DatabaseDaoObject.deleteAll(TableClassObject)
    }
}