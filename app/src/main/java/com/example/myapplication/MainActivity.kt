package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ViewModel.ViewModelClass
import com.example.myapplication.database.TableClass
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),IAdapterClass {

    lateinit var mViewModel:ViewModelClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //recyclerView is the id of recyclerView in xml layout
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterClass(this,this)
        recyclerView.adapter = adapter
        mViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelClass::class.java)
        mViewModel.allData.observe(this,Observer{ list -> list?.let{
            adapter.updateList(it) //update List is a function in Adapter that has notifyDataSetChanged() function.
            }
        })
    }
    override fun onItemClicked(TableClassObject: TableClass){
        mViewModel.deleteViewModelfun(TableClassObject)
        Toast.makeText(this,"${TableClassObject.text} Deleted", Toast.LENGTH_LONG).show()
    }
    fun sumbitData(view:View){
        val text = input.text.toString()
        if(text.isNotEmpty()){
            mViewModel.InsertViewModelfun(TableClass(text))
            Toast.makeText(this,"${text} Added", Toast.LENGTH_LONG).show()
        }
    }
}