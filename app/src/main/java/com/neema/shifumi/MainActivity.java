package com.neema.shifumi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.neema.shifumi.no_android.Player;
import com.neema.shifumi.no_android.PlayerType;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private boolean exitApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exitApp = false;

        Button vsHuman = findViewById(R.id.btn_vs_human);
        Button vsComputer = findViewById(R.id.btn_vs_computer);

        final Intent intent = new Intent(MainActivity.this, PlayActivity.class);

        vsHuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("VS_COMPUTER", false);
                intent.putExtra(Player.class.getName(), new Player(PlayerType.HUMAN));
                startActivity(intent);
                finish();
            }
        });

        vsComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(Player.class.getName(), new Player(PlayerType.COMPUTER));
                intent.putExtra("VS_COMPUTER", true);
                startActivity(intent);
                finish();
            }
        });
    }

    /*@Override
    public void onBackPressed() {
        if (exitApp) {
            super.onBackPressed();
            return;
        }
        this.exitApp = true;
        Toast.makeText(this, "Veuillez cliquer de nouveau sur BACK pour quitter", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitApp = false;
            }
        }, 1000);
    }*/
}
