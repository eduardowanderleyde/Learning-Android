package br.ufpe.cin.residencia.datamanagement.preferences;

import static br.ufpe.cin.residencia.datamanagement.MainActivity.KEY_USERNAME;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import br.ufpe.cin.residencia.datamanagement.R;

public class PreferenceManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_manual);
        final EditText campoTexto = findViewById(R.id.editTextUsername);
        final Button b = findViewById(R.id.salvarUsername);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString(KEY_USERNAME, "nenhum");
        campoTexto.setText(username);


        b.setOnClickListener(v -> {
            String oQueFoiDigitado = campoTexto.getText().toString();
            SharedPreferences.Editor e = prefs.edit();
            e.putString(KEY_USERNAME, oQueFoiDigitado);
            e.apply();
            finish();
        });
    }
}
