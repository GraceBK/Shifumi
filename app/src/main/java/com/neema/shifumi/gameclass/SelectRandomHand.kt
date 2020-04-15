package com.neema.shifumi.gameclass

import com.neema.shifumi.gameclass.enums.HandEnum

class SelectRandomHand : PlayerInterface {
    /**
     * Fonction qui permet de jouer un coup
     */
    override fun play(): HandEnum? {
        println("Je fais un choix aleatoire")
        return arrayOf(HandEnum.ROCK, HandEnum.PAPER, HandEnum.SCISSORS).random()
    }
}