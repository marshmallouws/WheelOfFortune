package com.example.wheeloffortune.vievmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _lives = MutableLiveData(5)
    val lives: LiveData<Int>
            get() = _lives

    private val _points = MutableLiveData(0)
    val points: LiveData<Int>
        get() = _points

    private val words : HashMap<String, List<String>>
            = HashMap<String, List<String>> ()

    init {
        words["Superheroes"] = listOf("Iron Man", "Captain Marvel", "Black Widow","Scarlet Witch")
        words["Villains"] = listOf("Joker", "Scarecrow", "Dr Octopus","Dr Doom")
    }



}