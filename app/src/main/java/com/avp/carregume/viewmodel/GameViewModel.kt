package com.avp.carregume.viewmodel

import android.R
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayMap
import com.avp.carregume.model.Cell
import com.avp.carregume.model.Game
import com.avp.carregume.model.Player
import com.avp.carregume.utils.StringUtility


class GameViewModel : ViewModel() {

    lateinit var cells: ObservableArrayMap<String, String>
    lateinit var tests: ObservableArrayMap<String, String>

    lateinit var game: Game

    fun init(player1: Array<String>, player2: Array<String>) {
        game = Game(player1, player2)
        cells = ObservableArrayMap()
        tests = ObservableArrayMap()

    }

    fun onClickedCellAt(row: Int, column: Int) {
        val imageView: String?

        if (game.cells[row][column] == null) {
            game.currentPlayer?.let { currentPlayer ->
                game.cells[row][column] = Cell(currentPlayer)
            }
            // en cours

            if (game.currentPlayer?.value.equals("tomate")){
                imageView = "tomate"
            } else if (game.currentPlayer?.value.equals("salad")) {
                imageView = "salad"
            } else if (game.currentPlayer?.value.equals("ognion")) {
                imageView = "ognion"
            } else {
                imageView = game.currentPlayer?.value
            }

            // text
            cells[StringUtility.stringFromNumbers(row, column)] = imageView

            if (game.hasGameEnded())
                game.reset()
            else
                game.switchPlayer()
        }
    }

    fun getWinner(): LiveData<Player> {
        return game.winner
    }


}