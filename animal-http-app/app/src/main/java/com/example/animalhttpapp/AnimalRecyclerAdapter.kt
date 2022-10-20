package com.example.animalhttpapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalhttpapp.models.Animal

class AnimalRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var animalsList = mutableListOf<Animal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AnimalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.animal_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AnimalViewHolder).bind(animalsList[position])
    }

    fun setList(dataList: MutableList<Animal>) {
        animalsList = dataList
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return animalsList.size
    }

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView
        private val typeTextView: TextView
        private val habitatTextView: TextView
        private val geoRangeTextView: TextView

        init {
            nameTextView = itemView.findViewById(R.id.animal_name)
            typeTextView = itemView.findViewById(R.id.animal_type)
            habitatTextView = itemView.findViewById(R.id.animal_habitat)
            geoRangeTextView = itemView.findViewById(R.id.geo_range)
        }

        fun bind(animal: Animal) {
            nameTextView.text = animal.name
            typeTextView.text =  animal.type
            habitatTextView.text = animal.habitat
            geoRangeTextView.text =  animal.geoRange
        }
    }
}