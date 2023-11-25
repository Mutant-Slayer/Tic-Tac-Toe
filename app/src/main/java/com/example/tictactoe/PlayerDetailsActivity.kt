package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class PlayerDetailsActivity : AppCompatActivity() {

    private lateinit var btnProceed: Button
    private lateinit var playerOne: EditText
    private lateinit var playerTwo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        btnProceed = findViewById(R.id.btnProceed)
        playerOne = findViewById(R.id.etPlayerOneName)
        playerTwo = findViewById(R.id.etPlayerTwoName)

        val iNext = Intent(this, MainActivity::class.java)

        btnProceed.setOnClickListener {
            iNext.putExtra("player1", playerOne.text.toString())
            iNext.putExtra("player2", playerTwo.text.toString())
            startActivity(iNext)
        }

        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                btnProceed.isEnabled =
                    !(playerOne.text.toString() == "" || playerTwo.text.toString() == "")
            }
        }

        playerOne.addTextChangedListener(watcher)
        playerTwo.addTextChangedListener(watcher)
    }

    override fun onResume() {
        playerOne.text = null
        playerTwo.text = null
        super.onResume()
    }
}