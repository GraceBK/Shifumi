package com.neema.shifumi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neema.shifumi.no_android.GameEngine;
import com.neema.shifumi.no_android.Hand;
import com.neema.shifumi.no_android.Player;
import com.neema.shifumi.no_android.PlayerType;

import java.util.Objects;

public class PlayActivity extends AppCompatActivity {

    private static final String TAG = PlayActivity.class.getName();

    private boolean vsComputer;
    private Player player;
    private Player otherPlayer;
    private GameEngine gameEngine;
    // View components
    TextView namePlayer, scoreWinPlayer, scoreLossPlayer;
    TextView nameOtherPlayer, scoreWinOtherPlayer, scoreLossOtherPlayer;

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
                setContentView(R.layout.human_vs_human);
            } else {
                setContentView(R.layout.human_vs_computer);
            }
        }
        initView();

        showPlayDialog();

        actionHandsBtn();

        Log.e(TAG, player.getNbWin()+"");
    }

    private void actionHandsBtn() {
        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.choose(Hand.ROCK);
                otherPlayer.chooseRandom();
                gameEngine.play();
                updateUI();
            }
        });
        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.choose(Hand.PAPER);
                otherPlayer.chooseRandom();
                gameEngine.play();
                updateUI();
            }
        });
        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.choose(Hand.SCISSORS);
                otherPlayer.chooseRandom();
                gameEngine.play();
                updateUI();
            }
        });

        if (!vsComputer) {
            btnRock2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.choose(Hand.ROCK);
                    otherPlayer.chooseRandom();
                    gameEngine.play();
                    updateUI();
                }
            });
            btnPaper2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.choose(Hand.PAPER);
                    otherPlayer.chooseRandom();
                    gameEngine.play();
                    updateUI();
                }
            });
            btnScissors2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.choose(Hand.SCISSORS);
                    otherPlayer.chooseRandom();
                    gameEngine.play();
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
        TextView resultP1 = dialogView.findViewById(R.id.tv_sc_data1);
        TextView resultP2 = dialogView.findViewById(R.id.tv_sc_data2);
        TextView resultNull = dialogView.findViewById(R.id.tv_sc_data3);
        nameP1.setText(namePlayer.getText());
        nameP2.setText(nameOtherPlayer.getText());
        resultP1.setText(scoreWinPlayer.getText());
        resultP2.setText(scoreWinOtherPlayer.getText());
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
        resultNull.setText("Partie null : " + player.getNbEgality());

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
        player.chooseRandom();
        otherPlayer.chooseRandom();
        gameEngine.match();
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        scoreWinPlayer.setText("Win : " + player.getNbWin());
        scoreLossPlayer.setText("Loss : " + player.getNbLoss());
        scoreWinOtherPlayer.setText("Win : " + otherPlayer.getNbWin());
        scoreLossOtherPlayer.setText("Loss : " + otherPlayer.getNbLoss());

        if (player.getNbWin() > 0 || otherPlayer.getNbWin() > 0) {
            ll_default.setVisibility(View.INVISIBLE);
            showReplayDialog();
            /*ll_default.setVisibility(View.INVISIBLE);
            int SPLASH_TIME_OUT = 500;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showReplayDialog();
                }
            }, SPLASH_TIME_OUT);*/
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
        }
    }

    private void initView() {
        namePlayer = findViewById(R.id.tv_name_default);
        namePlayer.setText(player.getType().name());
        scoreWinPlayer = findViewById(R.id.tv_sw_default);
        scoreLossPlayer = findViewById(R.id.tv_sl_default);

        nameOtherPlayer = findViewById(R.id.tv_name_other);
        nameOtherPlayer.setText(otherPlayer.getType().name());
        scoreWinOtherPlayer = findViewById(R.id.tv_sw_other);
        scoreLossOtherPlayer = findViewById(R.id.tv_sl_other);

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
        player = new Player(PlayerType.HUMAN);
        otherPlayer = (Player) getIntent().getSerializableExtra(Player.class.getName());
        player.setNbWin(0);
        player.setNbLoss(0);
        player.setNbEgality(0);

        otherPlayer.setNbWin(0);
        otherPlayer.setNbLoss(0);
        otherPlayer.setNbEgality(0);
        assert otherPlayer != null;
        gameEngine = new GameEngine(player, otherPlayer);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
}
