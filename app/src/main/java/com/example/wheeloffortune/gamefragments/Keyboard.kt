package com.example.wheeloffortune.gamefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Keyboard : Fragment() {
    // Skal indeholde alfabetet - skal fjerne bogstaver så snart et bogstav er gættet.
    private val choosenLetters = mutableListOf<Char>()
    private val lettersLeft = mutableListOf<Char>()

    fun chooseLetter(letter: Char){

    }
}