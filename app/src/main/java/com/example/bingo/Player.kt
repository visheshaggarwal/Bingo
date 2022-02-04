package com.example.bingo

class Player(private val row: Int, private val col: Int, private val scoreToWin: Int = 5) {
    private val ticket: ArrayList<ArrayList<Int>> = arrayListOf()
    private var crossedTicket: ArrayList<ArrayList<Int>>? = null
    var score = 0
    private val rowMap: MutableMap<Int,Int> = mutableMapOf()
    private val colMap: MutableMap<Int,Int> = mutableMapOf()
    private val leftDiagonal: MutableMap<Int,Int> = mutableMapOf()
    private val rightDiagonal: MutableMap<Int,Int> = mutableMapOf()
    private val maxValue = row*col

    init {
        val numbers = IntArray(row*col) {it+1}
        numbers.shuffle()
        crossedTicket = arrayListOf()

        (0 until maxValue step col).forEach { rowIndex ->
            val row = arrayListOf<Int>()
            (rowIndex until rowIndex+col).forEach {
                row.add(numbers[it])
            }
            ticket.add(row)
            crossedTicket!!.add(ArrayList(row))
        }
    }

    fun getTicket() = ticket

    fun getCrossedTicket() = crossedTicket

    fun crossNumber(number: Int): Array<Int> {

        ticket.forEachIndexed{ rowIndex, row ->
            val colIndex = row.indexOf(number)
            if(colIndex != -1) {
                crossedTicket!![rowIndex][colIndex] = -1
                return arrayOf(rowIndex, colIndex)
            }
        }

        return arrayOf(-1,-1)
    }

    fun updateScore(rowIndex: Int, colIndex: Int) {
        // Check Row Wise
        val rowScore = this.rowMap.getOrDefault(rowIndex,0)
        this.rowMap[rowIndex] = rowScore+1
        if(this.rowMap[rowIndex] == this.col)
            this.score++

        // Check Column Wise
        val colScore = this.colMap.getOrDefault(colIndex,0)
        this.colMap[colIndex] = colScore+1
        if(this.colMap[colIndex] == this.row)
            this.score++

        if(this.row == this.col) {
            // Check Left Diagonal
            val leftDiagonalScore = this.leftDiagonal.getOrDefault(rowIndex-colIndex,0)
            this.leftDiagonal[rowIndex-colIndex] = leftDiagonalScore + 1
            if(this.leftDiagonal[rowIndex - colIndex] == this.row){
                this.score++
            }

            // Check Right Diagonal
            val rightDiagonalScore = this.rightDiagonal.getOrDefault(rowIndex+colIndex,0)
            this.rightDiagonal[rowIndex+colIndex] = rightDiagonalScore + 1
            if(this.rightDiagonal[rowIndex + colIndex] == this.col){
                this.score++
            }
        }
    }

    fun isWinner() = score >= scoreToWin

}