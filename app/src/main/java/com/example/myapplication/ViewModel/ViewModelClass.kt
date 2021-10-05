package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repository.RepositoryClass
import com.example.myapplication.database.DatabaseName
import com.example.myapplication.database.TableClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelClass(application: Application) : AndroidViewModel(application) {
    val allData:LiveData<List<TableClass>>
    val repository:RepositoryClass
    init {
        val Dao = DatabaseName.getDatabase(application).DatabaseDaofun()
        repository = RepositoryClass(Dao)
        allData = repository.allData
    }
    fun deleteViewModelfun(TableClassObject:TableClass) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(TableClassObject)
    }
    fun InsertViewModelfun(TableClassObject: TableClass) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(TableClassObject)
    }
}