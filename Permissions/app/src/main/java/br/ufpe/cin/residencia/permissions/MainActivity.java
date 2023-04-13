package br.ufpe.cin.residencia.permissions;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_location;
    private Button btn_camera;
    private Button btn_internet;
    private Button btn_contacts;
    private Button btn_storage;
    private TextView msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_location = findViewById(R.id.btn_location);
        btn_camera = findViewById(R.id.btn_camera);
        btn_internet = findViewById(R.id.btn_internet);
        btn_contacts = findViewById(R.id.btn_contacts);
        btn_storage = findViewById(R.id.btn_storage);
        msg = findViewById(R.id.mensagem);

        btn_camera.setOnClickListener(
                v -> {

                }
        );

        btn_location.setOnClickListener(
                v -> {

                }
        );
        btn_storage.setOnClickListener(
                v -> {

                }
        );
        btn_contacts.setOnClickListener(
                v -> {

                }
        );

        btn_internet.setOnClickListener(
                v -> {

                }
        );
    }

}