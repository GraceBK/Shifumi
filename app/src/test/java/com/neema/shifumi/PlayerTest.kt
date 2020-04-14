package com.neema.shifumi

import com.neema.shifumi.no_android.Hand
import com.neema.shifumi.no_android.Player
import com.neema.shifumi.no_android.PlayerType
import org.junit.Assert.*
import org.junit.Test

class PlayerTest {

    var player1 = Player(PlayerType.HUMAN)
    var player2 = Player(PlayerType.COMPUTER)
    var hands = arrayOf(Hand.ROCK, Hand.PAPER, Hand.SCISSORS)

    @Test
    fun testGetType() {
        assertEquals(PlayerType.HUMAN, player1.type)
    }

    @Test
    fun testGetHands() {
        assertArrayEquals(hands, player2.hands)
    }

    @Test
    fun testChoose() {
        val chooseHand: Hand? = player1.choose(Hand.ROCK)
        assertEquals(Hand.ROCK, chooseHand)
    }

    private fun isInArray(hand: Hand): Boolean {
        for (item in hands) {
            if (hand == item) {
                return true
            }
        }
        return false
    }

    @Test
    fun testChooseRandom() {
        val chooseHand: Hand? = player2.chooseRandom()
        assertTrue(isInArray(chooseHand!!))
    }
}