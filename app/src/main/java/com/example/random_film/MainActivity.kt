package com.example.random_film

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val randomFilm: TextView = findViewById<TextView>(R.id.random_chosen_film)
        val caution = findViewById<TextView>(R.id.caution)
        val randomizeBtn = findViewById<Button>(R.id.randomize_btn)
        val resetBtn = findViewById<Button>(R.id.reset_btn)
        val films = resources.getStringArray(R.array.films)

        val filmsCopy = mutableListOf<String>()
        for (i in films) {
            filmsCopy.add(i)
        }

        fun setRandomFilm() {
            if (filmsCopy.size == 0) {
                caution.visibility = View.VISIBLE
                resetBtn.visibility = View.VISIBLE
            } else {
                val randomNum = (0..<filmsCopy.size).random()
                randomFilm.text = filmsCopy[randomNum]
                filmsCopy.removeAt(randomNum)
            }
        }

        fun reset() {
            for (i in films) {
                filmsCopy.add(i)
            }
            caution.visibility = View.GONE
            resetBtn.visibility = View.GONE
            randomFilm.text = ""

        }

        randomizeBtn.setOnClickListener() {
            setRandomFilm()
        }

        resetBtn.setOnClickListener() {
            reset()
        }
    }

}