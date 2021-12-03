package com.example.wheeloffortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.StringBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordTextView: TextView
    private lateinit var guessButton: Button
    private lateinit var inputLetter: EditText
    private lateinit var spinWheelButton: Button
    private lateinit var livesTextView: TextView
    private lateinit var pointsTextView: TextView
    private lateinit var categoryTextView: TextView
    private var word = ""
    private var lives = 5
    private var lettersUsed = mutableListOf<Char>()
    private val rnd = Random()
    private var underscoreWord = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordTextView = findViewById(R.id.word)
        guessButton = findViewById(R.id.guessBtn)
        inputLetter = findViewById(R.id.editTextInputLetter)
        spinWheelButton = findViewById(R.id.spinWheelBtn)
        livesTextView = findViewById(R.id.livesTextView)
        pointsTextView = findViewById(R.id.pointsTextView)
        categoryTextView = findViewById(R.id.categoryTextView)

        startGame()

        guessButton.setOnClickListener { guessLetter(inputLetter.text.toString()[0]) }


        if(lives == 0 )
            wordTextView.text = "You lost"

        if(word.equals(underscoreWord, true))
            wordTextView.text = "You won"
    }

    private fun startGame() {
        lettersUsed = mutableListOf()
        lives = 5
        livesTextView.text = "Lives: " + lives

        val rndKey = Words.words.entries.elementAt(rnd.nextInt(Words.words.size))
        word = rndKey.value[rnd.nextInt(rndKey.value.size)]
        categoryTextView.text = rndKey.key
        generateUnderScores(word, listOf())
    }

    private fun generateUnderScores(word: String, guessedLetters: List<Char>) {
        val lower = word.lowercase()
        val stringBuilder = StringBuilder()
        lower.forEach { char ->
            when {
                char == ' ' -> stringBuilder.append("  ")
                guessedLetters.contains(char) -> stringBuilder.append("$char")
                else -> stringBuilder.append("_ ")
            }
        }
        underscoreWord = stringBuilder.toString()
        wordTextView.text = stringBuilder.toString()
    }

    private fun guessLetter(letter: Char) {
        val lowerLetter = letter.lowercase()[0] // Need the lowercase, length is always 1
        if(lettersUsed.contains(lowerLetter)) {
            inputLetter.text.clear()
            return
        }
        lettersUsed.add(lowerLetter)

        if(word.lowercase().contains(lowerLetter)) {
            generateUnderScores(word, lettersUsed)
            inputLetter.text.clear()
            if(hasWon(word, underscoreWord)) {
                setWinnerScreen()
            }
        } else {
            inputLetter.text.clear()
            lives--
        }
        livesTextView.text = "Lives: " + lives
    }

    fun setWinnerScreen() {
        wordTextView.text = "Hurrahh"
        guessButton.visibility = View.INVISIBLE
        spinWheelButton.visibility = View.INVISIBLE
        inputLetter.isEnabled = false
    }

    /*
            wordTextView = findViewById(R.id.word)
        guessButton = findViewById(R.id.guessBtn)
        inputLetter = findViewById(R.id.editTextInputLetter)
        spinWheelButton = findViewById(R.id.spinWheelBtn)
        livesTextView = findViewById(R.id.livesTextView)
        pointsTextView = findViewById(R.id.pointsTextView)
        categoryTextView = findViewById(R.id.categoryTextView)
     */

    fun hasWon(word: String, underscoreWord: String): Boolean {
        return word.equals(underscoreWord, true)
    }

    fun gameOn(lives: Int, word: String, underscoreWord: String): Boolean{
        return lives > 0 && !word.equals(underscoreWord, true)
    }




}