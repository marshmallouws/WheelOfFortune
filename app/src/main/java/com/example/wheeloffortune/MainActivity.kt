package com.example.wheeloffortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.StringBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordTextView: TextView
    private lateinit var guessButton: Button
    private lateinit var inputLetter: EditText
    private var word = ""
    private var lives = 5
    private var lettersUsed = mutableListOf<Char>()
    private val rnd = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordTextView = findViewById(R.id.word)
        guessButton = findViewById(R.id.guessBtn)
        inputLetter = findViewById(R.id.editTextInputLetter)
        startGame()
    }

    private fun startGame() {
        lettersUsed = mutableListOf()
        lives = 5

        val rndKey = Words.words.entries.elementAt(rnd.nextInt(Words.words.size))
        word = rndKey.value[rnd.nextInt(rndKey.value.size)]
        wordTextView.text = word
        generateUnderScores(word, listOf())
        guessButton.setOnClickListener { guessLetter(inputLetter.text.toString()[0]) }
    }

    fun generateUnderScores(word: String, guessedLetters: List<Char>) {
        val stringBuilder = StringBuilder()
        word.forEach { char ->
            when {
                char == ' ' -> stringBuilder.append("  ")
                guessedLetters.contains(char) -> stringBuilder.append("$char ")
                else -> stringBuilder.append("_ ")
            }
        }
        wordTextView.text = stringBuilder.toString()
    }

    fun guessLetter(letter: Char) {
        if(lettersUsed.contains(letter)) {
            inputLetter.text.clear()
            return
        }
        lettersUsed.add(letter)

        if(word.lowercase().contains(letter, true)) {
            generateUnderScores(word, lettersUsed)
            inputLetter.text.clear()
            if(word.equals(wordTextView.text as String?, true)) {
                wordTextView.text = "Hurrahh"
            }
        } else {
            lives--
        }
    }

    fun hasLost(lives: Int): Boolean{
        return lives == 0
    }




}