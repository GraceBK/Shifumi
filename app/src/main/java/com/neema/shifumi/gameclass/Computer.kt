package com.neema.shifumi.gameclass

class Computer : Player() {
    init {
        this.hand = SelectRandomHand()
    }
}