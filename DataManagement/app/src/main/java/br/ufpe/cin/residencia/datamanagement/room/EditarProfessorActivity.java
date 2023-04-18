package br.ufpe.cin.residencia.datamanagement.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import br.ufpe.cin.residencia.datamanagement.R;

public class EditarProfessorActivity extends AppCompatActivity {
    ProfessorViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_professor);
        vm = new ViewModelProvider(this).get(ProfessorViewModel.class);
        Intent i = getIntent();
        String loginPassadoNoIntent = i.getStringExtra("login");
        Toast.makeText(this,loginPassadoNoIntent,Toast.LENGTH_SHORT).show();
        vm.buscarPeloLogin(loginPassadoNoIntent);

        Button btnInserir = findViewById(R.id.btnAtualizar);
        Button btnRemover = findViewById(R.id.btnRemover);
        EditText nome = findViewById(R.id.nome);
        EditText login = findViewById(R.id.login);
        Switch legal = findViewById(R.id.legal);
        login.setEnabled(false);

        vm.professorAtual.observe(
                this,
                professor -> {
                    nome.setText(professor.nome);
                    login.setText(professor.login);
                    legal.setChecked(professor.legal);
                }
        );

        btnInserir.setOnClickListener(
                v-> {
                    String nomeProfessor = nome.getText().toString();
                    String loginProfessor = login.getText().toString();
                    boolean legalProfessor = legal.isChecked();
                    String email = loginProfessor+"@cin.ufpe.br";
                    String site = "https://www.cin.ufpe.br/~"+loginProfessor;
                    Professor p = new Professor(nomeProfessor, loginProfessor, email, site, legalProfessor);
                    vm.atualizar(p);
                    finish();
                }
        );

        btnRemover.setOnClickListener(
                v -> {
                    vm.remover(vm.professorAtual.getValue());
                    finish();
                }
        );

    }
}