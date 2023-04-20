package br.ufpe.cin.residencia.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MusicPlayerNoBindingActivity extends AppCompatActivity {
    String activityID = Integer.toHexString(new Random().nextInt());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Button botaoPlay = findViewById(R.id.botaoPlay);
        Button botaoPause = findViewById(R.id.botaoPause);
        botaoPlay.setVisibility(View.GONE);
        botaoPause.setVisibility(View.GONE);
        Button btn_StopService = findViewById(R.id.botaoStopService);
        Button btn_StartService = findViewById(R.id.botaoStartService);
        btn_StopService.setVisibility(View.VISIBLE);
        btn_StartService.setVisibility(View.VISIBLE);
        TextView hashId = findViewById(R.id.hashId);
        hashId.setText("Activity ID: " + activityID);

        btn_StartService.setOnClickListener(v -> {
            startService(new Intent(this, MusicPlayerService.class));
        });

        btn_StopService.setOnClickListener(v -> {
            stopService(
                    new Intent(
                            this,
                            MusicPlayerService.class));
        });
    }
}