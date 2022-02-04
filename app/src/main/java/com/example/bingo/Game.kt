package com.example.bingo

import kotlin.collections.ArrayList

class Game(private val numOfPlayers: Int) {

    private val row = 5
    private val col = 5
    private val scoreToWin = 5
    private val players: ArrayList<Player> = arrayListOf()
    private val maxValue = row*col
    private val setOfNumbers = (1..maxValue).toMutableSet()

    init {
        (1..numOfPlayers).forEach{ _ ->
            players.add(Player(row,col,scoreToWin))
        }
    }

    fun drawNewNumber(): Int {
        val numberDrawn = setOfNumbers.random()
        setOfNumbers.remove(numberDrawn)
        return numberDrawn
    }

    fun isGameOver() = players.any{it.isWinner()}

    fun step(numberDrawn: Int) {
        players.forEach { player ->
            val tileIndex = player.crossNumber(numberDrawn)
            player.updateScore(tileIndex[0], tileIndex[1])
        }
    }

    fun getPlayerTickets(): ArrayList<ArrayList<ArrayList<Int> > > {
        val playerTickets: ArrayList<ArrayList<ArrayList<Int> > > = arrayListOf()
        players.forEach { player ->
            playerTickets.add(player.getTicket())
        }
        return playerTickets
    }

    fun getPlayerCrossedTicketById(playerIndex: Int): ArrayList<ArrayList<Int> >  = players[playerIndex].getCrossedTicket()!!

    fun getPlayerScoreById(playerIndex: Int): Int = players[playerIndex].score


    fun getWinners(): ArrayList<Int> {
        val winners = arrayListOf<Int>()
        players.forEachIndexed{ index, player ->
            if(player.isWinner()) {
                winners.add(index+1)
            }
        }
        return winners
    }

}