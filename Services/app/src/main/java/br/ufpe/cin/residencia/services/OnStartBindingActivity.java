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

public class OnStartBindingActivity extends AppCompatActivity {
    private int activityID = new Random().nextInt();
    private boolean bindingEstabelecido = false;

    private MusicPlayerBindingService musicPlayerService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bindingEstabelecido = true;
            MusicPlayerBindingService.MusicBinder binder = (MusicPlayerBindingService.MusicBinder) service;
            musicPlayerService = binder.pegarInstancia();
            Toast.makeText(getApplicationContext(), "Realizando binding...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindingEstabelecido = false;
            musicPlayerService = null;
            Toast.makeText(getApplicationContext(), "Desfazendo binding...", Toast.LENGTH_SHORT).show();
        }
    };

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
        botaoStartService.setVisibility(View.GONE);
        botaoStopService.setVisibility(View.GONE);
        botaoBindService.setVisibility(View.GONE);
        botaoUnbindService.setVisibility(View.GONE);
        TextView hashId = findViewById(R.id.hashId);
        hashId.setText(Integer.toHexString(activityID));

        startService(new Intent(this, MusicPlayerBindingService.class));

        botaoPlay.setOnClickListener(v -> {
            if (bindingEstabelecido) {
                musicPlayerService.playMusic();
            }
        });
        botaoPause.setOnClickListener(v -> {
            if (bindingEstabelecido) {
                musicPlayerService.pauseMusic();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!bindingEstabelecido) {
            Intent i = new Intent(this, MusicPlayerBindingService.class);
            i.putExtra("activityID", Integer.toHexString(activityID));
            bindService(
                    i,
                    serviceConnection,
                    Context.BIND_AUTO_CREATE
            );
        } else {
            Toast.makeText(this, "O binding já foi realizado...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        if (bindingEstabelecido) {
            bindingEstabelecido = false;
            unbindService(serviceConnection);
        } else {
            Toast.makeText(this, "O binding já foi desfeito, ou nunca realizado...", Toast.LENGTH_SHORT).show();
        }
        super.onStop();
    }

}