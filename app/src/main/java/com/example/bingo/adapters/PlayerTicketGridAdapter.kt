package com.example.bingo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.bingo.R

class PlayerTicketGridAdapter(var context: Context, var ticket: ArrayList<ArrayList<Int> >, var crossedTicket: ArrayList<ArrayList<Int> >) : BaseAdapter()  {
    override fun getCount(): Int {
        return ticket.size*ticket[0].size
    }

    override fun getItem(p0: Int): Any {
        return ticket[p0/5][p0%5]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ResourceAsColor")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.player_ticket_grid_item, null)
        val textView = view.findViewById<TextView>(R.id.tv_player_ticket_grid_item)
        textView.text = (ticket[p0/5][p0%5]).toString()
        if(isNumberCrossed(p0)) {
            textView.setBackgroundColor(Color.parseColor("#F10000"))
            textView.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            textView.setBackgroundColor(Color.parseColor("#E8E8E8"))
            textView.setTextColor(Color.parseColor("#000000"))
        }
        return view
    }

    private fun isNumberCrossed(index: Int): Boolean {
        return crossedTicket[index/5][index%5] == -1
    }

    fun setPlayerTicket(playerTicket: ArrayList<ArrayList<Int> >) {
        ticket = playerTicket
    }

    fun setPlayerCrossedTicket(crossedTicket: ArrayList<ArrayList<Int>>) {
        this.crossedTicket = crossedTicket
    }
}