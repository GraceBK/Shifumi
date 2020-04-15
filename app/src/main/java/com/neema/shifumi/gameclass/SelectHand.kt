package com.neema.shifumi.gameclass

import com.neema.shifumi.gameclass.enums.HandEnum

class SelectHand(handEnum: HandEnum?) : PlayerInterface {

    private val hand = handEnum
    private val hands = arrayOf(HandEnum.ROCK, HandEnum.PAPER, HandEnum.SCISSORS)

    /**
     * Fonction qui permet de jouer un coup
     */
    override fun play(): HandEnum? {
        println("Je fais un choix selectif")
        for (item in hands) {
            if (hand == item) {
                return item
            }
        }
        return null
    }
}