package com.neema.shifumi

import com.neema.shifumi.no_android.GameEngine
import com.neema.shifumi.no_android.Hand
import com.neema.shifumi.no_android.Player
import com.neema.shifumi.no_android.PlayerType
import org.junit.Assert.*
import org.junit.Test

class GameEngineTest {

    var player1 = Player(PlayerType.HUMAN)
    var player2 = Player(PlayerType.COMPUTER)
    var gameEngine = GameEngine(player1, player2)

    @Test
    fun testPlayEgality() {
        player1.choose(Hand.ROCK)
        player2.choose(Hand.ROCK)
        gameEngine.play()
        assertEquals(player1.nbEgality, player2.nbEgality)
    }

    @Test
    fun testPlayPlayer1Win() {
        player1.choose(Hand.SCISSORS)
        player2.choose(Hand.PAPER)
        gameEngine.play()
        assertTrue(player1.nbWin > player2.nbWin)
    }

    @Test
    fun testPlayPlayer2Loss() {
        player1.choose(Hand.PAPER)
        player2.choose(Hand.ROCK)
        gameEngine.play()
        assertTrue(player2.nbLoss > player1.nbLoss)
    }

    @Test
    fun testContinueParty() {
        // Premier coup
        player1.chooseRandom()
        player2.chooseRandom()
        gameEngine.play()
        val isFinished1: Boolean = gameEngine.continueParty()
        // Deuxieme coup
        player1.chooseRandom()
        player2.chooseRandom()
        gameEngine.play()
        val isFinished2: Boolean = gameEngine.continueParty()
        assertEquals(!isFinished1, !isFinished2)
    }

    @Test
    fun testMatch() {
        gameEngine.match()
        assertEquals(gameEngine.isFinished, true)
    }
}