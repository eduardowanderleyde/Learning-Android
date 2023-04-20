package br.ufpe.cin.residencia.services;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class WorkerThreadServiceActivity extends AppCompatActivity {
    String activityID = Integer.toHexString(new Random().nextInt());
    int numToasts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_operation);
        String msg = "Activity " + activityID + " foi criada na memória";
        log(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        Button btn_taskMainThread = findViewById(R.id.btn_taskMainThread);
        Button btn_StartServiceMainThread = findViewById(R.id.btn_StartServiceMainThread);
        Button btn_StopServiceMainThread = findViewById(R.id.btn_StopServiceMainThread);
        Button btn_toast = findViewById(R.id.btn_toast);
        TextView mensagem = findViewById(R.id.mensagem);

        btn_toast.setOnClickListener(
                v -> {
                    numToasts++;
                    Toast.makeText(
                            getApplicationContext(),
                            "Mensagem do toast (" + numToasts + ")",
                            Toast.LENGTH_LONG
                    ).show();
                    mensagem.setText("Total de toasts: " + numToasts);
                }
        );


        btn_taskMainThread.setOnClickListener(
                v -> {
                    try {
                        //Abstraindo uma operação que leva cerca de 4s para acontecer
                        Thread.sleep(4000);
                        //System.out.println
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        btn_StartServiceMainThread.setOnClickListener(
                v -> {

                }
        );

        btn_StopServiceMainThread.setOnClickListener(
                v -> {

                }
        );
    }

    @Override
    protected void onDestroy() {
        String msg = "Activity " + activityID + " prestes a ser removida da memória";
        log(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void log(String msg) {
        MainActivity.log(this.getClass().getSimpleName(), msg);
    }
}