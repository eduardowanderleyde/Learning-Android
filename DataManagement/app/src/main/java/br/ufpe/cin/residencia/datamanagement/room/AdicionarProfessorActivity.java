package br.ufpe.cin.residencia.datamanagement.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import br.ufpe.cin.residencia.datamanagement.R;

public class AdicionarProfessorActivity extends AppCompatActivity {
    ProfessorViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_professor);
        vm = new ViewModelProvider(this).get(ProfessorViewModel.class);

        Button btnInserir = findViewById(R.id.btnAtualizar);
        Button btnRemover = findViewById(R.id.btnRemover);
        EditText nome = findViewById(R.id.nome);
        EditText login = findViewById(R.id.login);
        Switch legal = findViewById(R.id.legal);

        btnInserir.setText("Inserir");
        btnRemover.setVisibility(View.GONE);
        btnInserir.setOnClickListener(
                v -> {
                    String nomeProfessor = nome.getText().toString();
                    String loginProfessor = login.getText().toString();
                    boolean legalProfessor = legal.isChecked();
                    String email = loginProfessor + "@cin.ufpe.br";
                    String site = "https://www.cin.ufpe.br/~" + loginProfessor;
                    Professor p = new Professor(nomeProfessor, loginProfessor, email, site, legalProfessor);
                    vm.inserir(p);
                    finish();

                }
        );
    }
}