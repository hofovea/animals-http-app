package com.example.animalhttpapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalhttpapp.models.Animal


class AnimalsListFragment : Fragment(R.layout.animals_fragment) {
    private lateinit var animalAdapter: AnimalRecyclerAdapter
    private val requestManager = RequestManager()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        //animalAdapter.setList(createAnimalList() as MutableList<Animal>)
        requestManager.setonAnimalListReceivedHandler { animalsList ->
            animalAdapter.setList(animalsList as MutableList<Animal>)
        }
        requestManager.execute()
    }

    private fun createAnimalList(): List<Animal> {
        val data = mutableListOf<Animal>()
        for (i in 0..10) {
            data.add(Animal())
        }
        return data
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.animal_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            animalAdapter = AnimalRecyclerAdapter()
            adapter = animalAdapter
        }
    }
}