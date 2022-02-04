package com.example.bingo.adapters

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.bingo.R

class PlayerAdapters(var context: Context, var arrayList: ArrayList<Int>) : BaseAdapter() {

    private var selectedPosition = 0

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.players_grid, null)
        var playerId = arrayList[p0]
        val textView = view.findViewById<TextView>(R.id.tv_players_grid_item)
        textView.text = playerId.toString()
        if(selectedPosition == p0) {
            textView.background = ContextCompat.getDrawable(
                context,
                R.drawable.selected_player_bg
            )
            textView.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            textView.background = ContextCompat.getDrawable(
                context,
                R.drawable.default_player_bg
            )
            textView.setTextColor(Color.parseColor("#000000"))
        }
        return view
    }

}