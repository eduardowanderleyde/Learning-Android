package br.ufpe.cin.residencia.background;

import static br.ufpe.cin.residencia.background.MainActivity.logThreadInfo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class CounterViewModelActivity extends AppCompatActivity {

    private TextView statusOperacao;
    private TextView contadorToasts;
    private Button botaoContagem;

    private CounterViewModel counterViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        counterViewModel = new ViewModelProvider(this).get(CounterViewModel.class);


        statusOperacao = findViewById(R.id.statusOperacao);
        contadorToasts = findViewById(R.id.contadorToasts);
        botaoContagem = findViewById(R.id.btnLongOperation);
        Button btnToast = findViewById(R.id.btnToast);

        btnToast.setOnClickListener(
                v -> {
                    logThreadInfo("Callback do botão de toasts");
                }
        );

        botaoContagem.setOnClickListener(
                v -> {
                    logThreadInfo("clicou no botão de contagem");
                }
        );


    }
}