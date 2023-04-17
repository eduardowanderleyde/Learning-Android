package br.ufpe.cin.residencia.background;

import static br.ufpe.cin.residencia.background.MainActivity.logThreadInfo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainThreadActivity extends AppCompatActivity {

    private int toastsGerados = 0;
    private int iteracoes = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Button btnToast = findViewById(R.id.btnToast);
        Button btnNetwork = findViewById(R.id.btnNetwork);
        Button btnLongOperation = findViewById(R.id.btnLongOperation);
        TextView contadorToasts = findViewById(R.id.contadorToasts);
        TextView contadorSegundos = findViewById(R.id.contaSegundos);
        TextView resultadoOperacaoLonga = findViewById(R.id.resultadoOperacao);
        TextView resultadoOperacaoRede = findViewById(R.id.resultadoOperacaoRede);

        contadorToasts.setText(getString(R.string.contadorToasts, toastsGerados));
        btnToast.setOnClickListener(
                v -> {
                    logThreadInfo("botão do Toast de MainThreadActivity");
                    toastsGerados++;
                    String msg = getString(R.string.contadorToasts, toastsGerados);
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    contadorToasts.setText(msg);
                }
        );

        btnLongOperation.setOnClickListener(
                v -> {
                    logThreadInfo("Iniciando operação longa");
                    resultadoOperacaoLonga.setText(R.string.comecouOperacao);
                }
        );

        btnNetwork.setOnClickListener(
                v -> {
                    logThreadInfo("Iniciando operação de rede");
                }
        );
    }


}