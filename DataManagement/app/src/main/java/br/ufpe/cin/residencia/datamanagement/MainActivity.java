package br.ufpe.cin.residencia.datamanagement;

import static br.ufpe.cin.residencia.datamanagement.preferences.SharedPreferencesActivity.KEY_HIGH_SCORE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import br.ufpe.cin.residencia.datamanagement.preferences.PreferenceManualActivity;
import br.ufpe.cin.residencia.datamanagement.preferences.PreferenceScreenActivity;
import br.ufpe.cin.residencia.datamanagement.preferences.SharedPreferencesActivity;
import br.ufpe.cin.residencia.datamanagement.room.ProfessoresActivity;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "uname";
    public static final String KEY_ESTADO = "estado";
    TextView nomeUsuario;
    TextView highScore;
    TextView estadoEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPreferences = findViewById(R.id.btnPreferences);
        Button btnPreferenceManual = findViewById(R.id.btnPreferenceManual);
        Button btnPreferenceScreen = findViewById(R.id.btnPreferenceScreen);
        Button btnRoom = findViewById(R.id.btnRoom);
        nomeUsuario = findViewById(R.id.nomeUsuario);
        highScore = findViewById(R.id.highScore);
        estadoEscolhido = findViewById(R.id.estadoEscolhido);



        btnPreferences.setOnClickListener(
                v -> startActivity(new Intent(this, SharedPreferencesActivity.class))
        );
        btnPreferenceManual.setOnClickListener(
                v -> startActivity(new Intent(this, PreferenceManualActivity.class))
        );
        btnPreferenceScreen.setOnClickListener(
                v -> startActivity(new Intent(this, PreferenceScreenActivity.class))
        );
        btnRoom.setOnClickListener(
                v -> startActivity(new Intent(this, ProfessoresActivity.class))
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int maiorPontuacao = prefs.getInt(KEY_HIGH_SCORE,0);
        highScore.setText(String.valueOf(maiorPontuacao));
        String username = prefs.getString(KEY_USERNAME, "nenhum");
        nomeUsuario.setText(username);
        String estado = prefs.getString(KEY_ESTADO, "nenhum selecionado");
        estadoEscolhido.setText(estado);

    }
}