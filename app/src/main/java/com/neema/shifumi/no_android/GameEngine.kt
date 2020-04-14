package com.neema.shifumi.no_android

import android.util.Log

class GameEngine(private var player1: Player, private var player2: Player) {

    var isFinished: Boolean? = false

    fun match() {
        while (continueParty()) {
            player1.chooseRandom()
            player2.chooseRandom()
            play()
            try {
                //TimeUnit.SECONDS.sleep(1)
                Thread.sleep(1_000)
                if (player1.nbWin || player2.nbWin) {
                    isFinished = true
                    break
                }
            } catch (e: InterruptedException) { e.printStackTrace() }
        }
    }

    fun continueParty(): Boolean {
        if (!player1.nbWin || !player2.nbWin) {
            return true
        }
        return false
    }

    fun play() {
        // ROCK > SCISSORS
        if (player1.myChoose?.equals(Hand.ROCK)!! && player2.myChoose?.equals(Hand.SCISSORS)!!) {   // ROCK > SCISSORS
            player1.nbWin = true
        } else if (player1.myChoose?.equals(Hand.SCISSORS)!! && player2.myChoose?.equals(Hand.PAPER)!!) {   // SCISSORS > PAPER
            player1.nbWin = true
        } else if (player1.myChoose?.equals(Hand.PAPER)!! && player2.myChoose?.equals(Hand.ROCK)!!) {   // PAPER > ROCK
            player1.nbWin = true
        } else if (player2.myChoose?.equals(Hand.SCISSORS)!! && player1.myChoose?.equals(Hand.PAPER)!!) {   // SCISSORS > PAPER
            player2.nbWin = true
        } else if (player2.myChoose?.equals(Hand.PAPER)!! && player1.myChoose?.equals(Hand.ROCK)!!) {   // PAPER > ROCK
            player2.nbWin = true
        } else if (player1.myChoose!! == player2.myChoose!!) {
            player1.nbEgality += 1
            player2.nbEgality += 1
        }
        println("GAME " + player1.toString() + " " + player1.myChoose)
        println("GAME " + player2.toString() + " " + player2.myChoose)
    }

}