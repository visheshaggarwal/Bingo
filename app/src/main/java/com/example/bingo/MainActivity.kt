package com.example.bingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_play.setOnClickListener{
            if(et_players_count.text.toString().isNotEmpty()) {
                try{
                    when(val playersCount = et_players_count.text.toString().toInt()) {
                        in (2..5) -> {
                            val intent = Intent(
                                this,
                                BingoTicketsActivity::class.java
                            )
                            intent.putExtra(Constants.PLAYER_COUNT, playersCount)
                            startActivity(intent)
                            finish()
                        }
                        else -> {
                            Toast.makeText(
                                this,
                                "count must be between 2 to 5",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch(e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        "Invalid input",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Enter number of players",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}