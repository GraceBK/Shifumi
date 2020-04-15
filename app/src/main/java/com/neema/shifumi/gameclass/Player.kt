package com.neema.shifumi.gameclass

import com.neema.shifumi.gameclass.enums.HandEnum

abstract class Player {
    // instance de comportement
    protected var hand: PlayerInterface = SelectRandomHand()

    var myHand: HandEnum? = null
    var winner: Boolean = false
    var egality: Boolean = false

    fun chooseHand() {
        myHand = hand.play()
        println("setPlayerHand $myHand")
    }

    fun setPlayerHand(handEnum: HandEnum) {
        this.hand = SelectHand(handEnum)
    }

    fun versus(player: Player): Player? {
        if (this.myHand == HandEnum.ROCK && player.myHand == HandEnum.SCISSORS
                || this.myHand == HandEnum.SCISSORS && player.myHand == HandEnum.PAPER
                || this.myHand == HandEnum.PAPER && player.myHand == HandEnum.ROCK
                || this.myHand != null && player.myHand == null) {
            println("${this::class} WIN")
            this.winner = true
            player.winner = false
            egality = false
            return this
        } else if (player.myHand == HandEnum.ROCK && this.myHand == HandEnum.SCISSORS
                || player.myHand == HandEnum.SCISSORS && this.myHand == HandEnum.PAPER
                || player.myHand == HandEnum.PAPER && this.myHand == HandEnum.ROCK
                || player.myHand != null && this.myHand == null) {
            println("${player::class} WIN")
            player.winner = true
            this.winner = false
            egality = false
            return player
        } else {
            println("NULL")
            winner = false
            egality = true
            return null
        }
    }

    override fun toString(): String {
        return "$hand"
    }

}