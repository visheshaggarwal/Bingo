package com.example.bingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private var winners: ArrayList<Int>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        winners = intent.getIntegerArrayListExtra(Constants.WINNERS)

        tv_name.text = getWinnerMessage()

        btn_finish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getWinnerMessage(): String {

        return if(winners!!.size == 1)
            "Player ${winners!![0]}"
        else {
            var winnerMessage = "Players "
            winners!!.forEach { player ->
                winnerMessage += "$player, "
            }
            winnerMessage.substring(0,winnerMessage.length - 2)
        }
    }
}