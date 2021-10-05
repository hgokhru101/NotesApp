package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.TableClass

class AdapterClass(private val context: Context, private val listener:IAdapterClass) :RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {
    val allData = ArrayList<TableClass>()

    inner class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deletebutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val viewHolder = ViewHolderClass(LayoutInflater.from(context).inflate(R.layout.item,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allData[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = allData[position]
        holder.textView.text = currentItem.text
    }

    fun updateList(newList: List<TableClass>){
        allData.clear()
        allData.addAll(newList)
        notifyDataSetChanged()
    }
}
interface IAdapterClass{
    fun onItemClicked(TableClassObject:TableClass)
}