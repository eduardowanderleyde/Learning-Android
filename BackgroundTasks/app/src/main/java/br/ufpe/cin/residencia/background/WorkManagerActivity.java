package br.ufpe.cin.residencia.background;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class WorkManagerActivity extends AppCompatActivity {
    private int toastsGerados = 0;
    private int iteracoes = 50;

    public static final String KEY_CONTENT = "conteudoBaixado";
    public static final String KEY_URL_DOWNLOAD = "urlParaDownload";

    Button btnToast;
    Button btnNetwork;
    Button btnLongOperation;
    TextView contadorToasts;
    TextView contadorSegundos;
    TextView resultadoOperacaoLonga;
    TextView resultadoOperacaoRede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.logThreadInfo("Rodando o onCreate");

        setContentView(R.layout.activity_tasks);

        btnToast = findViewById(R.id.btnToast);
        btnNetwork = findViewById(R.id.btnNetwork);
        btnLongOperation = findViewById(R.id.btnLongOperation);
        contadorToasts = findViewById(R.id.contadorToasts);
        contadorSegundos = findViewById(R.id.contaSegundos);
        resultadoOperacaoLonga = findViewById(R.id.resultadoOperacao);
        resultadoOperacaoRede = findViewById(R.id.resultadoOperacaoRede);

        contadorToasts.setText(getString(R.string.contadorToasts, toastsGerados));

        btnToast.setOnClickListener(
                v -> {
                    MainActivity.logThreadInfo("Callback do botão de toasts");
                    toastsGerados++;
                    String msg = getString(R.string.contadorToasts, toastsGerados);
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    Log.d("MainThreadAPP", msg);
                    contadorToasts.setText(msg);
                }
        );

        btnLongOperation.setOnClickListener(
                v -> Toast.makeText(this, "Não implementado nesta activity", Toast.LENGTH_SHORT).show()
        );

        btnNetwork.setOnClickListener(
                v -> {
                    MainActivity.logThreadInfo("Callback do botão de operação de rede");
                    btnNetwork.setEnabled(false);
                }
        );

    }
}