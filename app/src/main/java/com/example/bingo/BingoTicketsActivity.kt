package com.example.bingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.bingo.adapters.PlayerAdapters
import com.example.bingo.adapters.PlayerTicketGridAdapter
import kotlinx.android.synthetic.main.activity_bingo_tickets.*

class BingoTicketsActivity : AppCompatActivity(), AdapterView.OnItemClickListener, View.OnClickListener{

    private var numOfPlayers: Int = 2
    private var game: Game? = null
    private var playerTickets: ArrayList<ArrayList<ArrayList<Int> > >? = null
    private var playerAdapters: PlayerAdapters? = null
    private var playerTicketGridAdapters: PlayerTicketGridAdapter? = null
    private var currentPlayer = 0
    private var winners: ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bingo_tickets)

        numOfPlayers = intent.getIntExtra(Constants.PLAYER_COUNT, 2)
        val arrayList = arrayListOf<Int>()
        (1..numOfPlayers).forEach { arrayList.add(it) }
        playerAdapters = PlayerAdapters(applicationContext, arrayList)
        gv_players.numColumns = numOfPlayers
        gv_players.adapter = playerAdapters
        gv_players.onItemClickListener = this

        game = Game(numOfPlayers)
        playerTickets = game!!.getPlayerTickets()

        playerTicketGridAdapters = PlayerTicketGridAdapter(applicationContext, playerTickets!![0], game!!.getPlayerCrossedTicketById(0))
        gv_player_ticket.adapter = playerTicketGridAdapters
        gv_player_ticket.isExpanded = true
        updatePlayerView()

        btn_draw.setOnClickListener(this)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        playerAdapters!!.setSelectedPosition(position)
        playerAdapters!!.notifyDataSetChanged()
        currentPlayer = position
        updatePlayerView()
    }

    private fun updatePlayerView() {
        tv_player.text = "Player ${currentPlayer+1}"
        playerTicketGridAdapters!!.setPlayerTicket(playerTickets!![currentPlayer])
        updateTicketView()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_draw -> {
                if(winners != null) {
                    val intent = Intent(
                        this,
                        ResultActivity::class.java
                    )
                    intent.putExtra(Constants.WINNERS, winners)
                    startActivity(intent)
                    finish()
                }
                else {
                    val numberDrawn = game!!.drawNewNumber()
                    tv_chosen_number.text = "Chosen Number: $numberDrawn"
                    tv_chosen_number.visibility = View.VISIBLE
                    btn_draw.text = "Draw Next Number"
                    game!!.step(numberDrawn)
                    updateTicketView()

                    if(game!!.isGameOver()) {
                        winners = game!!.getWinners()
                        btn_draw.text = "Results"
                    }
                }
            }
        }
    }

    private fun updateTicketView() {
        tv_score.text = "Score: ${game!!.getPlayerScoreById(currentPlayer)}"
        playerTicketGridAdapters!!.setPlayerCrossedTicket(game!!.getPlayerCrossedTicketById(currentPlayer))
        playerTicketGridAdapters!!.notifyDataSetChanged()
    }

}