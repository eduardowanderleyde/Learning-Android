package br.ufpe.cin.residencia.background;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String JSON_URL = "https://jsonplaceholder.typicode.com/todos/1";
    public static final String LOGTAG = "BackgroundTasksApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logThreadInfo("onCreate() de MainActivity");

        Button btnToast = findViewById(R.id.btnToast);
        Button btnMainThread = findViewById(R.id.btnMainThread);
        Button btnCounterNoThread = findViewById(R.id.btnCounterNoThreads);
        Button btnCounterThreads = findViewById(R.id.btnCounterThreads);
        Button btnThreads = findViewById(R.id.btnThreads);
        Button btnCounterViewModel = findViewById(R.id.btnCounterViewModel);
        Button btnWorkManager = findViewById(R.id.btnWorkManager);

        btnToast.setOnClickListener(
                v -> {
                    Toast.makeText(this, "Mensagem qualquer", Toast.LENGTH_SHORT).show();
                    logThreadInfo("botão do Toast de MainActivity");
                }
        );

        btnMainThread.setOnClickListener(
                v -> {
                    startActivity(new Intent(this, MainThreadActivity.class));
                    logThreadInfo("botão de abrir MainThreadActivity");
                }
        );
        btnThreads.setOnClickListener(
                v -> {
                    logThreadInfo("botão de abrir OffMainThreadActivity");
                    startActivity(new Intent(this, OffMainThreadActivity.class));
                }
        );
        btnWorkManager.setOnClickListener(
                v -> startActivity(new Intent(this, WorkManagerActivity.class))
        );
        btnCounterNoThread.setOnClickListener(
                v -> startActivity(new Intent(this, CounterNoThreadsActivity.class))
        );
        btnCounterThreads.setOnClickListener(
                v -> startActivity(new Intent(this, CounterThreadsActivity.class))
        );
        btnCounterViewModel.setOnClickListener(
                v -> startActivity(new Intent(this, CounterViewModelActivity.class))
        );
    }

    public static void logThreadInfo(String msg) {
        Log.i(LOGTAG, msg + " | Name: " + Thread.currentThread().getName() + " | ID: " + Thread.currentThread().getId());
    }

    public static String getContentFromURL(String link) throws IOException {
        InputStream in = null;
        String result = "";
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            byte[] response = out.toByteArray();
            result = new String(response, "UTF-8");
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

}