package com.denobaba.notepadddeneme.adaptor

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denobaba.notepadddeneme.NotepaddInformation
import com.denobaba.notepadddeneme.databinding.RecycleRowBinding
import com.denobaba.notepadddeneme.model.datas

class myadaptor(val placeList: List<datas>) : RecyclerView.Adapter<myadaptor.mydataholder>() {
    class mydataholder(val recycleRowBinding: RecycleRowBinding) : RecyclerView.ViewHolder(recycleRowBinding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mydataholder {
        val recycleRowBinding = RecycleRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return mydataholder(recycleRowBinding)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: mydataholder, position: Int) {
        holder.recycleRowBinding.recyclerowtext.text= placeList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,NotepaddInformation::class.java)
            intent.putExtra("selecteddatas",placeList.get(position))
            holder.itemView.context.startActivity(intent)
        }


    }



}





