package com.avp.carregume.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avp.carregume.R
import com.avp.carregume.databinding.ActivityGameBinding
import com.avp.carregume.model.Player
import com.avp.carregume.utils.StringUtility.Companion.isNullOrEmpty
import com.avp.carregume.viewmodel.GameViewModel


class GameActivity : AppCompatActivity() {

    private val GAME_vegetable_DIALOG_TAG = "game_dialog_tag"

    private val GAME_BEGIN_DIALOG_TAG = "game_dialog_tag"
    private val GAME_END_DIALOG_TAG = "game_end_dialog_tag"
    private val NO_WINNER = "No one"

    private lateinit var gameViewModel: GameViewModel

    var player1: String  = ""
    var player2: String  = ""

    var legumeName1: String  = ""
    var legumeName2: String  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vegetableEntry()
        promptForPlayers()

    }

    fun promptForPlayers() {
        val beginDialog =
            GameBeginDialog.newInstance(this)
        beginDialog.isCancelable = false
        beginDialog.show(supportFragmentManager, GAME_BEGIN_DIALOG_TAG)
    }

    fun onPlayersSet(player1: String, player2: String) {
        this@GameActivity.player1 = player1
        this@GameActivity.player2 = player2
    }

    // test new alert
    fun vegetableEntry() {
        val dialog =
            chooseVegetableDialog.newInstance(this)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_vegetable_DIALOG_TAG)
    }

    fun onVegetableSet(legumeName1: String, legumeName2: String) {
        this@GameActivity.legumeName1 = legumeName1
        this@GameActivity.legumeName2 = legumeName2
        initDataBinding()
    }

    private fun initDataBinding() {
        val player1:  Array<String> = arrayOf(this@GameActivity.player1, this@GameActivity.legumeName1)
        val player2:  Array<String> = arrayOf(this@GameActivity.player2, this@GameActivity.legumeName2)

        val activityGameBinding = DataBindingUtil.setContentView<ActivityGameBinding>(this, R.layout.activity_game)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        gameViewModel.init(player1, player2)
        activityGameBinding.gameViewModel = gameViewModel
        setUpOnGameEndListener()
    }

    private fun setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this, Observer { player ->
            if (player != null) {
                onGameWinnerChanged(player)
            } else {
                showDialogEndGame("No Winner")
            }
        })
    }

    private fun onGameWinnerChanged(winner: Player) {
        val winnerName = if (isNullOrEmpty(winner.name)) NO_WINNER else winner.name
        showDialogEndGame(winnerName)
    }

    private fun showDialogEndGame(nameWinner: String) {
        val dialog = GameEndDialog.newInstance(
            this,
            nameWinner
        )
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_END_DIALOG_TAG)
    }
}