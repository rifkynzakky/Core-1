package com.example.core1app

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    //Declare for the counter value
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Declaring and initializing the variables of the textview and button
        var textView= findViewById<TextView>(R.id.totalNumber)

        val scoreButton = findViewById<Button>(R.id.scoreButton)
        val stealButton = findViewById<Button>(R.id.stealButton)
        val resetButton = findViewById<Button>(R.id.resetButton)

        var clapSound = MediaPlayer.create(this, R.raw.clap)

        //Function for SCORE (add 1 to the total number)
        scoreButton.setOnClickListener {
            if (count < 15) {
                count++
            } else {
                count = 15
                clapSound.start()
            }
            textView.text = "$count"

            // If statement to change the color of the text
            if (count < 5) {
                textView.setTextColor(Color.BLACK)
            } else if (count < 10) {
                textView.setTextColor(Color.BLUE)
            } else if (count < 15) {
                textView.setTextColor(Color.GREEN)
            }
        }

        // Function for STEAL (reduce by 1 to the total number when clicked)
        stealButton.setOnClickListener {
            count--

            textView.text = "$count"

            // If statement to change the color of the text
            if (count < 5) {
                textView.setTextColor(Color.BLACK)
            } else if (count < 10) {
                textView.setTextColor(Color.BLUE)
            } else if (count < 15) {
                textView.setTextColor(Color.GREEN)
            } else {
                textView.setTextColor(Color.BLACK)
            }
        }

        // Function to reset the value to 0
        resetButton.setOnClickListener{
            count = 0
            val textView = findViewById<TextView>(R.id.totalNumber)
            textView.text = "$count"
            textView.setTextColor(Color.BLACK)

            // Log used for debugging and information in the LOG
            Log.i("MainActivity", "Reseted value is $count")
        }

    }

    // Function to save the value
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val userNumber = count
        outState.putInt("savedValue", count)

        // Log used for debugging and information in the LOG
        Log.i("MainActivity","Saved Instance $count")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val userInt = savedInstanceState.getInt("savedValue")
        count = userInt

        // Log used for debugging and information in the LOG
        Log.i("MainActivity","Restore Instance $count")
    }
}