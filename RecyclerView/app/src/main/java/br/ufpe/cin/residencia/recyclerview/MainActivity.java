package br.ufpe.cin.residencia.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPessoas = findViewById(R.id.btnPessoas);
        Button btnCard = findViewById(R.id.btnCard);
        Button btnLifecycle = findViewById(R.id.btnLifecycle);
        Button btnAnimation = findViewById(R.id.btnAnimation);

        btnPessoas.setOnClickListener(
                v -> startActivity(new Intent(this, RecyclerViewPessoasActivity.class))
        );
        btnCard.setOnClickListener(
                v -> startActivity(new Intent(this, RecyclerViewCardActivity.class))
        );
        btnLifecycle.setOnClickListener(
                v -> startActivity(new Intent(this, RecyclerViewEventsActivity.class))
        );
        btnAnimation.setOnClickListener(
                v -> startActivity(new Intent(this, RecyclerViewAnimationActivity.class))
        );
    }
}