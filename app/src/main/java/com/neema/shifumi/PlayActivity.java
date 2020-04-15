package com.neema.shifumi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.neema.shifumi.gameclass.Player;
import com.neema.shifumi.gameclass.enums.HandEnum;

import java.util.Objects;

public class PlayActivity extends AppCompatActivity {

    private static final String TAG = PlayActivity.class.getName();

    private boolean vsComputer;
    private Player player;
    private Player otherPlayer;
    //private GameEngine gameEngine;

    // View components
    private LinearLayout ll_default;
    private ImageButton btnRock, btnPaper, btnScissors;
    private ImageButton btnRock2, btnPaper2, btnScissors2;

    ImageView handPlayer, handOtherPlayer;
    ImageView iv_hand1, iv_hand2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        {
            initParty();
            vsComputer = Objects.requireNonNull(getIntent().getExtras()).getBoolean("VS_COMPUTER");
            if (!vsComputer) {
                setContentView(R.layout.computer_vs_computer);
            } else {
                setContentView(R.layout.human_vs_computer);
            }
        }
        initView();

        showPlayDialog();

        actionHandsBtn();

        //Log.e(TAG, player.getWinner()+"");
    }

    private void actionHandsBtn() {
        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayerHand(HandEnum.ROCK);
//                otherPlayer. chooseRandom();
//                gameEngine.play();
                updateUI();
            }
        });
        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayerHand(HandEnum.PAPER);
//                otherPlayer.chooseRandom();
//                gameEngine.play();
                updateUI();
            }
        });
        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayerHand(HandEnum.SCISSORS);
//                otherPlayer.chooseRandom();
//                gameEngine.play();
                updateUI();
            }
        });

        if (!vsComputer) {
            btnRock2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.setPlayerHand(HandEnum.ROCK);
//                    otherPlayer.chooseRandom();
//                    gameEngine.play();
                    updateUI();
                }
            });
            btnPaper2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.setPlayerHand(HandEnum.PAPER);
//                    otherPlayer.chooseRandom();
//                    gameEngine.play();
                    updateUI();
                }
            });
            btnScissors2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.setPlayerHand(HandEnum.SCISSORS);
//                    otherPlayer.chooseRandom();
//                    gameEngine.play();
                    updateUI();
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    private void showReplayDialog() {
        // before inflating the custom alert dialog layout, we will get the current activity viewGroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_custom_replay, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this/*, R.style.CustomAlertDialog*/);
        builder.setCancelable(false);

        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// Supprime le background

        TextView nameP1 = dialogView.findViewById(R.id.tv_sc_player1);
        TextView nameP2 = dialogView.findViewById(R.id.tv_sc_player2);
        ImageView resultP1 = dialogView.findViewById(R.id.iv_winner1);
        ImageView resultP2 = dialogView.findViewById(R.id.iv_winner2);
        //TextView resultNull = dialogView.findViewById(R.id.tv_sc_data3);
        /*nameP1.setText("" + player.getType());
        nameP2.setText("" + otherPlayer.getType());
        if (player.getNbWin()) {
            resultP1.setImageResource(R.drawable.ic_trophy);
        }

        if (!player.getNbWin()) {
            resultP1.setImageResource(0);
        }

        if (otherPlayer.getNbWin()) {
            resultP2.setImageResource(R.drawable.ic_trophy);
        }

        if (!otherPlayer.getNbWin()) {
            resultP2.setImageResource(0);
        }

        iv_hand1 = dialogView.findViewById(R.id.iv_hand1);
        iv_hand2 = dialogView.findViewById(R.id.iv_hand2);

        if (player.getMyChoose() == Hand.ROCK) {
            iv_hand1.setImageResource(R.drawable.ic_rock);
        }
        if (player.getMyChoose() == Hand.PAPER) {
            iv_hand1.setImageResource(R.drawable.ic_paper);
        }
        if (player.getMyChoose() == Hand.SCISSORS) {
            iv_hand1.setImageResource(R.drawable.ic_scissors);
        }

        if (otherPlayer.getMyChoose() == Hand.ROCK) {
            iv_hand2.setImageResource(R.drawable.ic_rock);
        }
        if (otherPlayer.getMyChoose() == Hand.PAPER) {
            iv_hand2.setImageResource(R.drawable.ic_paper);
        }
        if (otherPlayer.getMyChoose() == Hand.SCISSORS) {
            iv_hand2.setImageResource(R.drawable.ic_scissors);
        }
        resultNull.setText("Partie null : " + player.getNbEgality());*/

        Button start = dialogView.findViewById(R.id.btn_replay);
        Button stop = dialogView.findViewById(R.id.btn_exit);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                handPlayer.setVisibility(View.INVISIBLE);
                handOtherPlayer.setVisibility(View.INVISIBLE);
                if (vsComputer) {
                    ll_default.setVisibility(View.VISIBLE);
                }
                initParty();
                updateUI();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                startActivity(new Intent(view.getContext(), MainActivity.class));
                finish();
            }
        });
        alertDialog.show();
    }

    private void showPlayDialog() {
        // before inflating the custom alert dialog layout, we will get the current activity viewGroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_custom, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this/*, R.style.CustomAlertDialog*/);
        builder.setCancelable(false);

        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// Supprime le background
        Button start = dialogView.findViewById(R.id.btn_play);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                int SPLASH_TIME_OUT = 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (vsComputer) {
                            ll_default.setVisibility(View.VISIBLE);
                        }
                        initParty();
                        updateUI();
                    }
                }, SPLASH_TIME_OUT);
            }
        });
        alertDialog.show();
    }



    private void shifumi() {
        /*player.chooseRandom();
        otherPlayer.chooseRandom();
        gameEngine.match();*/
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {

        /*if (player.getNbWin() || otherPlayer.getNbWin()) {
            //ll_default.setVisibility(View.INVISIBLE);
            showReplayDialog();
        }

        if (player.getMyChoose() != null) {
            handPlayer.setVisibility(View.VISIBLE);
            handOtherPlayer.setVisibility(View.VISIBLE);
        }
        if (player.getMyChoose() == Hand.ROCK) {
            handPlayer.setImageResource(R.drawable.ic_rock);
        }
        if (player.getMyChoose() == Hand.PAPER) {
            handPlayer.setImageResource(R.drawable.ic_paper);
        }
        if (player.getMyChoose() == Hand.SCISSORS) {
            handPlayer.setImageResource(R.drawable.ic_scissors);
        }

        if (otherPlayer.getMyChoose() == Hand.ROCK) {
            handOtherPlayer.setImageResource(R.drawable.ic_rock);
        }
        if (otherPlayer.getMyChoose() == Hand.PAPER) {
            handOtherPlayer.setImageResource(R.drawable.ic_paper);
        }
        if (otherPlayer.getMyChoose() == Hand.SCISSORS) {
            handOtherPlayer.setImageResource(R.drawable.ic_scissors);
        }*/
    }

    private void initView() {
        ll_default = findViewById(R.id.ll_chooser_default);
        btnRock = findViewById(R.id.choose_rock);
        btnPaper = findViewById(R.id.choose_paper);
        btnScissors = findViewById(R.id.choose_scissors);

        if (!vsComputer) {
            btnRock2 = findViewById(R.id.choose_rock2);
            btnPaper2 = findViewById(R.id.choose_paper2);
            btnScissors2 = findViewById(R.id.choose_scissors2);
        }

        handPlayer = findViewById(R.id.tv_hand_human);
        handOtherPlayer = findViewById(R.id.tv_hand_other);

        updateUI();
    }

    private void initParty() {
        /*player = new Player(PlayerType.HUMAN);
        otherPlayer = (Player) getIntent().getSerializableExtra(Player.class.getName());
        player.setNbWin(false);
        player.setNbEgality(0);

        otherPlayer.setNbWin(false);
        otherPlayer.setNbEgality(0);
        assert otherPlayer != null;
        gameEngine = new GameEngine(player, otherPlayer);*/
    }
}
