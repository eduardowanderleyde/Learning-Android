package br.ufpe.cin.residencia.background;

import static br.ufpe.cin.residencia.background.MainActivity.logThreadInfo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CounterNoThreadsActivity extends AppCompatActivity {
    private int toastsGerados = 0;
    private int limiteContador = 5;

    private TextView statusOperacao;
    private TextView contadorToasts;
    private Button botaoContagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        statusOperacao = findViewById(R.id.statusOperacao);
        contadorToasts = findViewById(R.id.contadorToasts);
        botaoContagem = findViewById(R.id.btnLongOperation);
        Button btnToast = findViewById(R.id.btnToast);

        btnToast.setOnClickListener(
                v -> {
                    logThreadInfo("Callback do botão de toasts");
                    toastsGerados++;
                    String msg = getString(R.string.contadorToasts, toastsGerados);
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    Log.d("MainThreadAPP", msg);
                    contadorToasts.setText(msg);
                }
        );

        botaoContagem.setOnClickListener(
                v -> {
                    logThreadInfo("clicou no botão de iniciar contagem");
                }
        );
    }

    private void iniciarContagem() throws InterruptedException {
        logThreadInfo("iniciando contagem");

        logThreadInfo("encerrando contagem");
    }
}