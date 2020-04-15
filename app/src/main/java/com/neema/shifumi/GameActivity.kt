package com.neema.shifumi

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.neema.shifumi.gameclass.Computer
import com.neema.shifumi.gameclass.Human
import com.neema.shifumi.gameclass.Player
import com.neema.shifumi.gameclass.enums.HandEnum
import kotlinx.android.synthetic.main.human_vs_computer.*

class GameActivity : AppCompatActivity() {

    companion object {
        private val TAG = GameActivity::class.java.simpleName
        private var vsComputer: Boolean = false
    }

    // retarder l’initialisation d’une variable non nul (lateinit)
    private lateinit var human: Player
    private lateinit var computer: Player
    private lateinit var computers: Array<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "------------------ $TAG OnCreate ------------------ ")

        vsComputer = intent.extras?.getBoolean("VS_COMPUTER") ?: false
        initParty()
        if (!vsComputer) {
            setContentView(R.layout.computer_vs_computer)
            showStartGameDialog()
            //actionAutoPlay()
        } else {
            setContentView(R.layout.human_vs_computer)
            showStartGameDialog()
        }
    }

    private fun actionAutoPlay() {
        var count = 0
        if (computers.size == 2) {
            // https://developer.android.com/reference/kotlin/android/os/CountDownTimer
            val timer = object: CountDownTimer(3000, 1000) {
                override fun onFinish() {
                    Log.e(TAG, "good finish")
                    computers[0].chooseHand()
                    computers[1].chooseHand()
                    updateUI(computers[0], computers[1])
                    play(computers[0], computers[1])
                }

                override fun onTick(p0: Long) {
                    Log.e(TAG, "good No winner $p0")
                }

            }
            timer.start()
            /*timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        Log.e(TAG, "good No winner $count")
                        //myTextView.setText("count=$count")
                        count++
                    }
                }
            }, 1000, 1000)*/
        }
    }

    private fun actionPlay() {
        if (vsComputer) {
            choose_rock.setOnClickListener {
                iPlay(HandEnum.ROCK)
            }
            choose_paper.setOnClickListener {
                iPlay(HandEnum.PAPER)
            }
            choose_scissors.setOnClickListener {
                iPlay(HandEnum.SCISSORS)
            }
        }
    }

    private fun iPlay(handEnum: HandEnum) {
        tv_hand_human.visibility = View.INVISIBLE
        tv_hand_other.visibility = View.INVISIBLE
        val timer = object: CountDownTimer(3000, 1000) {
            override fun onFinish() {
                computer.chooseHand()
                Log.e(TAG, "[${computer::class.java.simpleName}] choose ${computer.myHand}")
                human.setPlayerHand(handEnum)
                human.chooseHand()
                Log.e(TAG, "[${human::class.java.simpleName}] choose ${human.myHand}")
                updateUI(human, computer)
                play(human, computer)
            }

            override fun onTick(p0: Long) {
                when ((p0/1000).toInt()) {
                    2 -> tv_timer.text = "Shi"
                    1 -> tv_timer.text = "ShiFu"
                    else -> tv_timer.text = "ShiFumi"
                }
            }
        }
        timer.start()
    }

    /**
     * Mets a jour l'affichage des coup joue par les deux parties
     */
    private fun updateUI(player1: Player, player2: Player) {
        if (player1.myHand != null || player2.myHand !== null) {
            tv_hand_human.visibility = View.VISIBLE
            tv_hand_other.visibility = View.VISIBLE
        }
        if (player1.myHand == HandEnum.ROCK) {
            tv_hand_human.setImageResource(R.drawable.ic_rock)
        }
        if (player1.myHand == HandEnum.PAPER) {
            tv_hand_human.setImageResource(R.drawable.ic_paper)
        }
        if (player1.myHand == HandEnum.SCISSORS) {
            tv_hand_human.setImageResource(R.drawable.ic_scissors)
        }
        if (player2.myHand == HandEnum.ROCK) {
            tv_hand_other.setImageResource(R.drawable.ic_rock)
        }
        if (player2.myHand == HandEnum.PAPER) {
            tv_hand_other.setImageResource(R.drawable.ic_paper)
        }
        if (player2.myHand == HandEnum.SCISSORS) {
            tv_hand_other.setImageResource(R.drawable.ic_scissors)
        }
    }

    private fun play(player1: Player, player2: Player) {
        val winner = player1.versus(player2)
        if (winner is Player) {
            Log.e(TAG, "[${winner::class.java.simpleName}] WIN ******* ${winner.winner}")
            val timer = object: CountDownTimer(1000, 1000) {
                override fun onFinish() {
                    showReplayDialog(player1, player2)
                }

                override fun onTick(p0: Long) {
                    Log.i(TAG, "$p0")
                }
            }
            timer.start()
        }
        if (winner == null) {
            if (!vsComputer) {
                val timer = object: CountDownTimer(1000, 1000) {
                    override fun onFinish() {
                        actionAutoPlay()
                    }

                    override fun onTick(p0: Long) {
                        Log.i(TAG, "$p0")
                    }
                }
                timer.start()
            }
        }
    }

    private fun showReplayDialog(player1: Player, player2: Player) {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_custom_replay, viewGroup, false)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // Supprime le background

        val nameP1 = dialogView.findViewById<TextView>(R.id.tv_sc_player1)
        val nameP2 = dialogView.findViewById<TextView>(R.id.tv_sc_player2)
        val resultP1 = dialogView.findViewById<ImageView>(R.id.iv_winner1)
        val resultP2 = dialogView.findViewById<ImageView>(R.id.iv_winner2)
        nameP1.text = player1::class.java.simpleName
        nameP2.text = player2::class.java.simpleName
        if (player1.winner) {
            resultP1.setImageResource(R.drawable.ic_trophy)
            resultP2.setImageResource(0)
        }
        if (player2.winner) {
            resultP2.setImageResource(R.drawable.ic_trophy)
            resultP1.setImageResource(0)
        }

        val ivHand1 = dialogView.findViewById<ImageView>(R.id.iv_hand1)
        val ivHand2 = dialogView.findViewById<ImageView>(R.id.iv_hand2)
        if (player1.myHand == HandEnum.ROCK) {
            ivHand1.setImageResource(R.drawable.ic_rock)
        }
        if (player1.myHand == HandEnum.PAPER) {
            ivHand1.setImageResource(R.drawable.ic_paper)
        }
        if (player1.myHand == HandEnum.SCISSORS) {
            ivHand1.setImageResource(R.drawable.ic_scissors)
        }
        if (player2.myHand == HandEnum.ROCK) {
            ivHand2.setImageResource(R.drawable.ic_rock)
        }
        if (player2.myHand == HandEnum.PAPER) {
            ivHand2.setImageResource(R.drawable.ic_paper)
        }
        if (player2.myHand == HandEnum.SCISSORS) {
            ivHand2.setImageResource(R.drawable.ic_scissors)
        }

        val replay = dialogView.findViewById<Button>(R.id.btn_replay)
        val exit = dialogView.findViewById<Button>(R.id.btn_exit)
        replay.setOnClickListener {
            alertDialog.dismiss()
            tv_hand_human.visibility = View.INVISIBLE
            tv_hand_other.visibility = View.INVISIBLE
            initParty()
            if (!vsComputer) {
                actionAutoPlay()
            } else {
                actionPlay()
            }
        }
        exit.setOnClickListener {
            alertDialog.dismiss()
            startActivity(Intent(it.context, MainActivity::class.java))
            finish()
        }

        alertDialog.show()
    }


    private fun showStartGameDialog() {
        // before inflating the custom alert dialog layout, we will get the current activity viewGroup
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_custom, viewGroup, false)

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(dialogView)

        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // Supprime le background
        val tvVS = dialogView.findViewById<TextView>(R.id.tv_type_party)
        if (!vsComputer) {
            tvVS.text = getString(R.string.computer_vs_computer)
        } else {
            tvVS.text = getString(R.string.you_vs_computer)
        }
        val start = dialogView.findViewById<Button>(R.id.btn_play)
        start.setOnClickListener {
            alertDialog.dismiss()
            if (!vsComputer) {
                actionAutoPlay()
            } else {
                actionPlay()
            }
        }
        alertDialog.show()
    }

    /**
     * Fonction qui initialise nos joueurs
     * Elle prend en parametre (de type Boolean) pour le choix de partie
     * (Human vs Computer) ou (Computer vs Computer)
     */
    private fun initParty() {
        if (!vsComputer) {
            computers = arrayOf(Computer(), Computer())
        } else {
            human = Human()
            computer = Computer()
        }
    }
}

//https://developer.android.com/reference/kotlin/android/os/CountDownTimer