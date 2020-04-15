package com.neema.shifumi.gameclass

import com.neema.shifumi.gameclass.enums.HandEnum

interface PlayerInterface {
    /**
     * Fonction qui permet de jouer un coup
     */
    fun play(): HandEnum?
}