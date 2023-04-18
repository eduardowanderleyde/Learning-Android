package br.ufpe.cin.residencia.datamanagement.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Random;
import br.ufpe.cin.residencia.datamanagement.R;

public class SharedPreferencesActivity extends AppCompatActivity {
    public static final String KEY_HIGH_SCORE = "recordePontuacao";
    private int maiorPontuacao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        //maior pontuação
        TextView recorde = findViewById(R.id.high_score_text);
        TextView pontuacaoAtual = findViewById(R.id.game_score_text);
        Button jogar = findViewById(R.id.play_button);
        Button resetar = findViewById(R.id.reset_button);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        maiorPontuacao = prefs.getInt(KEY_HIGH_SCORE,0);
        recorde.setText(String.valueOf(maiorPontuacao));

        jogar.setOnClickListener(
                v -> {
                    int novoNumero = new Random().nextInt(100000);
                    pontuacaoAtual.setText(String.valueOf(novoNumero));
                    if (novoNumero > maiorPontuacao) {
                        //o novo número é o recorde agora
                        maiorPontuacao = novoNumero;
                        recorde.setText(String.valueOf(novoNumero));
                        SharedPreferences.Editor e = prefs.edit();
                        e.putInt(KEY_HIGH_SCORE, maiorPontuacao);
                        e.apply();
                    }

                }
        );

        resetar.setOnClickListener(
                v -> {
                    pontuacaoAtual.setText("");
                    recorde.setText(String.valueOf(0));
                    maiorPontuacao = 0;
                    SharedPreferences.Editor e = prefs.edit();
                    e.remove(KEY_HIGH_SCORE);
                    e.apply();
                }
        );
    }

}