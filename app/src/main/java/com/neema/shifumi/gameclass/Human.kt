package com.neema.shifumi.gameclass

import com.neema.shifumi.gameclass.enums.HandEnum

class Human : Player() {
    init {
        this.hand = SelectHand(null)
    }

    fun setHand(handEnum: HandEnum) {
        this.hand = SelectHand(handEnum)
    }
}