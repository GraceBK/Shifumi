package com.neema.shifumi.no_android

import android.util.Log
import java.io.Serializable
import java.util.concurrent.TimeUnit

open class Player(playerType: PlayerType) : Serializable {

    val type: PlayerType = playerType
    var myChoose: Hand? = null

    var hands = arrayOf(Hand.ROCK, Hand.PAPER, Hand.SCISSORS)

    var nbWin: Int = 0
    var nbLoss: Int = 0
    var nbEgality: Int = 0

    /**
     * Function return a Hand we have selected
     */
    fun choose(hand: Hand): Hand? {
        var result: Hand? = null
        for (item in hands) {
            if (hand == item) {
                result = item
                this.myChoose = hand
            }
        }
        return result
    }

    /**
     * Function return a Hand (random)
     */
    fun chooseRandom(): Hand? {
        Log.i("GAME", "Wait computer choose")
        TimeUnit.MICROSECONDS.sleep(1)
        val hand = hands.random()
        this.myChoose = hand
        return hand
    }

    /**
     * Function who show a hand list to string
     */
    private fun toStringHands(): String {
        val res = StringBuilder("{ ")
        for (hand in hands) {
            res.append(hand).append(" ")
        }
        res.append("}")
        return res.toString()
    }

    /**
     * Function who show a Player Object
     */
    override fun toString(): String {
        return "Player {" +
                " player : " + type +
                ", win : " + nbWin +
                ", loss : " + nbLoss +
                ", egality : " + nbEgality +
                ", hand : " + toStringHands() +
                " }"
    }

}