package br.ufpe.cin.residencia.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewPessoasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        //pegar a referência para o objeto exibido na tela, do tipo RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PessoaAdapter(Constants.professores));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        );
    }

    //Classe que representa o adapter
    //estabelece como transfoormar objetos Java em widgets de UI
    class PessoaAdapter extends RecyclerView.Adapter<PessoaViewHolder> {

        Pessoa[] pessoas;
        public PessoaAdapter(Pessoa[] listaInicial) {
            this.pessoas = listaInicial;
        }
        @NonNull
        @Override
        public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //R.layout.pessoa
            View v = getLayoutInflater().inflate(R.layout.pessoa,parent,false);
            PessoaViewHolder viewHolder = new PessoaViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(
                @NonNull PessoaViewHolder holder,
                int position) {
            Pessoa p = this.pessoas[position];
            holder.nome.setText(p.getNome());
            holder.login.setText(p.getLogin());
            if (p.isLegal()) {
                holder.icone.setImageResource(R.drawable.ok);
            }
            else {
                holder.icone.setImageResource(R.drawable.delete);
            }
        }
        @Override
        public int getItemCount() {
            return this.pessoas.length;
        }
    }

    class PessoaViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView login;
        ImageView icone;

        public PessoaViewHolder(@NonNull View linhaDoRecyclerView) {
            super(linhaDoRecyclerView);
            this.nome = linhaDoRecyclerView.findViewById(R.id.nome);
            this.login = linhaDoRecyclerView.findViewById(R.id.login);
            this.icone = linhaDoRecyclerView.findViewById(R.id.icone);

        }
    }

}