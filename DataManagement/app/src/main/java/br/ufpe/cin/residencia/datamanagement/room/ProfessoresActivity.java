package br.ufpe.cin.residencia.datamanagement.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufpe.cin.residencia.datamanagement.R;


public class ProfessoresActivity extends AppCompatActivity {

    ProfessorAdapter adapter;
    ProfessorDB db;
    ProfessorViewModel vm;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professores);
        adapter = new ProfessorAdapter(getLayoutInflater());
        vm = new ViewModelProvider(this).get(ProfessorViewModel.class);

        Button adicionarProfessor = findViewById(R.id.btn_Adiciona);
        RecyclerView rv = findViewById(R.id.rvPessoas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
//
//        db = Room.databaseBuilder(
//                getApplicationContext(),
//                ProfessorDB.class,
//                ProfessorDB.DB_NAME
//        ).build();

        vm.professores.observe(
                this,
                novaLista -> {
                    Log.d("BANCODEDADOS", String.valueOf(novaLista.size()));
                    List<Professor> novaListaProfs = new ArrayList<>(novaLista);
                    adapter.atualizarProfessores(novaListaProfs);
                }
        );

//        int i = 0;
//        while (i<10) {
//            i++;
//            String login = "login"+i;
//            String email = login+"@cin.ufpe.br";
//            String site = "https://www.cin.ufpe.br/~"+login;
//            vm.inserir(new Professor("Nome "+i, login, email, site, new Random().nextInt(20)%2==0));
//        }


        adicionarProfessor.setOnClickListener(
                v -> {
                    startActivity(new Intent(this, AdicionarProfessorActivity.class));
//                    new Thread(
//                            () -> {
////                                ProfessorDAO dao = db.dao();
////                                int num = 1;//new Random().nextInt(1000);
////                                String login = "teste"+num;
////                                String nome = "Teste "+num;
////
////                                Professor p = new Professor(
////                                        nome,
////                                        login,
////                                        login+"@cin.ufpe.br",
////                                        "https://www.cin.ufpe.br/~"+login,
////                                        num%2==0
////                                );
////                                dao.inserir(p);
////                                runOnUiThread(
////                                        () -> adapter.adicionarProfessor(p)
////                                );
////
////                                Professor x = dao.buscarPeloLogin("teste1");
////                                Log.d("BANCODEDADOS", x.toString());
//                            }
//                    ).start();

                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
//        new Thread(
//                () -> {
//                    List<Professor> professores = db.dao().todosProfessores();
//                    runOnUiThread(
//                            () -> adapter.atualizarProfessores(professores)
//                    );
//                }
//        ).start();
        //adapter.notifyDataSetChanged();
    }
}