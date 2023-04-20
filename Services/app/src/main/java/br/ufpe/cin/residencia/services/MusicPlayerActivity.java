package br.ufpe.cin.residencia.services;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MusicPlayerActivity extends AppCompatActivity {
    String activityID = Integer.toHexString(new Random().nextInt());
    private MediaPlayer mediaPlayer;
    private boolean estaTocandoMusica = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        mediaPlayer = MediaPlayer.create(this, R.raw.ghosts);
        String msg = "Activity " + activityID + " foi criada na memória";
        log(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        Button startButton = findViewById(R.id.botaoPlay);
        Button pauseButton = findViewById(R.id.botaoPause);
        TextView hashId = findViewById(R.id.hashId);
        hashId.setText("Activity ID: " + activityID);

        startButton.setOnClickListener(v -> {
            mediaPlayer.start();
            estaTocandoMusica = true;

        });

        pauseButton.setOnClickListener(v -> {
            if (estaTocandoMusica){
                mediaPlayer.pause();
                estaTocandoMusica = false;
            }


        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (estaTocandoMusica) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        if (estaTocandoMusica) {
            mediaPlayer.pause();
        }
        super.onStop();
    }

    private void log(String msg) {
        MainActivity.log(this.getClass().getSimpleName(), msg);
    }

}