package br.ufpe.cin.residencia.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MusicPlayerWithBindingActivity extends AppCompatActivity {
    String activityID = Integer.toHexString(new Random().nextInt());
    private boolean bindingEstabelecido = false;
    private MusicPlayerBindingService musicPlayerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Button botaoPlay = findViewById(R.id.botaoPlay);
        Button botaoPause = findViewById(R.id.botaoPause);
        Button botaoStartService = findViewById(R.id.botaoStartService);
        Button botaoStopService = findViewById(R.id.botaoStopService);
        Button botaoBindService = findViewById(R.id.botaoBindService);
        Button botaoUnbindService = findViewById(R.id.botaoUnbindService);
        botaoStartService.setVisibility(View.VISIBLE);
        botaoStopService.setVisibility(View.VISIBLE);
        botaoBindService.setVisibility(View.VISIBLE);
        botaoUnbindService.setVisibility(View.VISIBLE);
        TextView hashId = findViewById(R.id.hashId);
        hashId.setText(activityID);

        botaoPlay.setOnClickListener(v -> {
            if (bindingEstabelecido) {
                musicPlayerService.playMusic();
            }
            else {
                Toast.makeText(this, "O binding já foi desfeito, ou nunca realizado...", Toast.LENGTH_SHORT).show();
            }

        });
        botaoPause.setOnClickListener(v -> {
            if (bindingEstabelecido) {
                musicPlayerService.pauseMusic();
            }
            else {
                Toast.makeText(this, "O binding já foi desfeito, ou nunca realizado...", Toast.LENGTH_SHORT).show();
            }
        });
        botaoStartService.setOnClickListener(v -> {
            startService(new Intent(this, MusicPlayerBindingService.class));
        });
        botaoStopService.setOnClickListener(v -> {
            stopService(new Intent(this, MusicPlayerBindingService.class));
        });
        botaoBindService.setOnClickListener(v -> {
            if (!bindingEstabelecido) {
                Intent i = new Intent(this, MusicPlayerBindingService.class);
                bindService(
                        i,
                        serviceConnection,
                        Context.BIND_AUTO_CREATE
                );
            }
            else {
                Toast.makeText(this, "O binding já foi realizado...", Toast.LENGTH_SHORT).show();
            }
        });
        botaoUnbindService.setOnClickListener(v -> {
            if (bindingEstabelecido) {
                unbindService(serviceConnection);
                bindingEstabelecido = false;
            }
            else {
                Toast.makeText(this, "O binding já foi desfeito, ou nunca realizado...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicPlayerBindingService.MusicBinder binder = (MusicPlayerBindingService.MusicBinder) service;
            musicPlayerService = binder.pegarInstancia();
            Toast.makeText(getApplicationContext(), "Realizando binding...", Toast.LENGTH_SHORT).show();
            bindingEstabelecido = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindingEstabelecido = false;
            musicPlayerService = null;
            Toast.makeText(getApplicationContext(), "Desfazendo binding...", Toast.LENGTH_SHORT).show();
        }
    };
}