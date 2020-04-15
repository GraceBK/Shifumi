package com.neema.shifumi

import com.neema.shifumi.gameclass.Computer
import com.neema.shifumi.gameclass.Human
import com.neema.shifumi.gameclass.Player
import com.neema.shifumi.gameclass.enums.HandEnum
import org.junit.Assert.*
import org.junit.Test

class GameTest {

    private val computer = Computer()
    private val human = Human()
    private val human2 = Human()

    /**
     * Test si on retourne un match null
     */
    @Test
    fun testNullMatch() {
        human.setPlayerHand(HandEnum.ROCK)
        human.chooseHand()
        human2.setPlayerHand(HandEnum.ROCK)
        human2.chooseHand()
        val player = human.versus(human2)
        assertNull(player)
    }

    /**
     * Je test si y a un gagnant
     */
    @Test
    fun testWinner() {
        human.setPlayerHand(HandEnum.ROCK)
        human.chooseHand()
        human2.setPlayerHand(HandEnum.PAPER)
        human2.chooseHand()
        val player = human2.versus(human)
        assertTrue(player is Player)
    }

    /**
     * Je verifie si le le coup joué par l'utilisateur correspond au coup selectionné
     */
    @Test
    fun testHumanPlay() {
        human.setPlayerHand(HandEnum.ROCK)
        human.chooseHand()
        assertTrue(human.myHand is HandEnum)
        assertEquals(HandEnum.ROCK, human.myHand)
    }

    /**
     * computer retourne une main aleatoire donc je teste si c'est bien un object de type HandEnum
     */
    @Test
    fun testComputerPlay() {
        computer.chooseHand()
        assertTrue(computer.myHand is HandEnum)
    }

}