package com.example.tictactoe

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button

    private lateinit var b1: String
    private lateinit var b2: String
    private lateinit var b3: String
    private lateinit var b4: String
    private lateinit var b5: String
    private lateinit var b6: String
    private lateinit var b7: String
    private lateinit var b8: String
    private lateinit var b9: String

    private lateinit var playerName: TextView
    private lateinit var animation: LottieAnimationView
    private lateinit var gameGroup: Group
    private lateinit var view: View

    private var gameStatus: MutableLiveData<Int> = MutableLiveData(0)
    private var flag: MutableLiveData<Int> = MutableLiveData(0)
    private var moves = 0
    private val handler = Handler(Looper.getMainLooper())

    private lateinit var playerOne: String
    private lateinit var playerTwo: String
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        playerOne = intent.getStringExtra("player1")!!
        playerTwo = intent.getStringExtra("player2")!!

        animation.visibility = View.GONE

        flag.observe(this) {
            when (it) {
                0 -> if (gameStatus.value == 0) playerName.text = "$playerOne turn"
                else -> if (gameStatus.value == 0) playerName.text = "$playerTwo turn"
            }
        }

        gameStatus.observe(this) {
            when (it) {
                1 -> {
                    animation.visibility = View.VISIBLE
                    animation.playAnimation()
                    view.visibility = View.VISIBLE
                    gameGroup.visibility = View.GONE
                    handler.postDelayed({
                        recreate()
                    }, 2000)
                }
            }
        }
    }

    private fun init() {
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)

        playerName = findViewById(R.id.tvPlayerName)
        animation = findViewById(R.id.lavWon)
        gameGroup = findViewById(R.id.gameGroup)
        view = findViewById(R.id.view)
    }

    fun setValue(v: View) {

        val currentBtn: Button = v as Button
        if (currentBtn.text == "") {
            moves++

            if (flag.value == 0) {
                currentBtn.text = "X"
                flag.value = 1
            } else {
                currentBtn.text = "O"
                flag.value = 0
            }

            if (moves > 4) {
                checkWinner()
            }
        }
    }

    fun restart(v: View) {
        resetGame()
    }

    private fun checkWinner() {
        b1 = btn1.text.toString()
        b2 = btn2.text.toString()
        b3 = btn3.text.toString()
        b4 = btn4.text.toString()
        b5 = btn5.text.toString()
        b6 = btn6.text.toString()
        b7 = btn7.text.toString()
        b8 = btn8.text.toString()
        b9 = btn9.text.toString()

        if (b1 != "" && b1 == b2 && b2 == b3) {
            showToast()
            gameStatus.value = 1
            resetGame()
        } else if (b4 != "" && b4 == b5 && b5 == b6) {
            showToast()
            gameStatus.value = 1
            resetGame()
        } else if (b7 != "" && b7 == b8 && b8 == b9) {
            showToast()
            gameStatus.value = 1
            resetGame()
        } else if (b1 != "" && b1 == b4 && b4 == b7) {
            showToast()
            gameStatus.value = 1
            resetGame()
        } else if (b2 != "" && b2 == b5 && b5 == b8) {
            showToast()
            gameStatus.value = 1
            resetGame()
        } else if (b3 != "" && b3 == b6 && b6 == b9) {
            showToast()
            gameStatus.value = 1
            resetGame()
        } else if (b1 != "" && b1 == b5 && b5 == b9) {
            showToast()
            gameStatus.value = 1
            resetGame()
        } else if (b3 != "" && b3 == b5 && b5 == b7) {
            showToast()
            gameStatus.value = 1
            resetGame()
        } else if (moves == 9) {
            Toast.makeText(this, "Game is drawn", Toast.LENGTH_LONG).show()
            resetGame()
        }
    }

    private fun showToast() {
        if (moves % 2 == 0) {
            Toast.makeText(this, "Winner is $playerOne", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Winner is $playerTwo", Toast.LENGTH_LONG).show()
        }
    }

    private fun resetGame() {
        handler.postDelayed({
            btn1.text = ""
            btn2.text = ""
            btn3.text = ""
            btn4.text = ""
            btn5.text = ""
            btn6.text = ""
            btn7.text = ""
            btn8.text = ""
            btn9.text = ""
            moves = 0
            flag.value = 0
        }, 2000)
    }
}

