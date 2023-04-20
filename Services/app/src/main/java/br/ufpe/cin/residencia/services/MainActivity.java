package br.ufpe.cin.residencia.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao_serviceMainThread = findViewById(R.id.botao_serviceMainThread);
        Button botao_serviceWorkerThread = findViewById(R.id.botao_serviceWorkerThread);
        Button botao_musicPlayer = findViewById(R.id.botao_musicPlayer);
        Button botao_musicServiceWithoutBinding = findViewById(R.id.botao_musicServiceWithoutBinding);
        Button botao_musicServiceWithBinding = findViewById(R.id.botao_musicServiceWithBinding);
        Button botao_anotherBinding = findViewById(R.id.botao_anotherBinding);

        botao_serviceMainThread.setOnClickListener(
                v -> startActivity(
                        new Intent(this, MainThreadServiceActivity.class)
                )
        );

        botao_serviceWorkerThread.setOnClickListener(
                v -> startActivity(
                        new Intent(this, WorkerThreadServiceActivity.class)
                )
        );

        botao_musicPlayer.setOnClickListener(
                v -> startActivity(
                        new Intent(this, MusicPlayerActivity.class)
                )
        );

        botao_musicServiceWithoutBinding.setOnClickListener(
                v -> startActivity(
                        new Intent(this, MusicPlayerNoBindingActivity.class)
                )
        );

        botao_musicServiceWithBinding.setOnClickListener(
                v -> startActivity(
                        new Intent(this, MusicPlayerWithBindingActivity.class)
                )
        );

        botao_anotherBinding.setOnClickListener(
                v -> startActivity(
                        new Intent(this, OnStartBindingActivity.class)
                )
        );
    }

    public static void log(String className, String msg) {
        Log.d(className, msg);
    }
}