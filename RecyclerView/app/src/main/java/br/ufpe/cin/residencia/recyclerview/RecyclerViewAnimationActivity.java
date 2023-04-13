package br.ufpe.cin.residencia.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class RecyclerViewAnimationActivity extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_animation);

        Button b = findViewById(R.id.botao);
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //Associa ao recycler view um adapter
        ProfessorAdapter a = new ProfessorAdapter();
        rv.setAdapter(a);

        b.setOnClickListener(
                v -> {
                    int indice = new Random().nextInt(Constants.professores.length);
                    a.adicionaProfessor(Constants.professores[indice]);
                }
        );


    }

    //ADAPTER
    // Classe que estabelece como transformar objetos, neste caso específico, do tipo Professor,
    // em representações visuais na tela
    class ProfessorAdapter extends RecyclerView.Adapter<ProfessorViewHolder> {

        SortedList<Pessoa> professores;

        public ProfessorAdapter() {
            this.professores = new SortedList<Pessoa>(
                    Pessoa.class,
                    new SortedList.Callback<Pessoa>() {
                        @Override
                        public int compare(Pessoa p1, Pessoa p2) {
                            return p1.getNome().compareTo(p2.getNome());
                        }

                        @Override
                        public boolean areContentsTheSame(Pessoa oldItem, Pessoa newItem) {
                            return oldItem.getLogin().equals(newItem.getLogin());
                        }

                        @Override
                        public boolean areItemsTheSame(Pessoa p1, Pessoa p2) {
                            return p1.getLogin().equals(p2.getLogin());
                        }

                        @Override
                        public void onChanged(int position, int count) {
                            notifyItemRangeChanged(position, count);
                        }

                        @Override
                        public void onInserted(int position, int count) {
                            notifyItemRangeInserted(position, count);
                        }

                        @Override
                        public void onRemoved(int position, int count) {
                            notifyItemRangeRemoved(position, count);
                        }

                        @Override
                        public void onMoved(int fromPosition, int toPosition) {
                            notifyItemMoved(fromPosition, toPosition);
                        }
                    }
            );
        }

        void adicionaProfessor(Pessoa p) {
            this.professores.add(p);
        }

        @NonNull
        @Override
        public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProfessorViewHolder(getLayoutInflater().inflate(R.layout.pessoa_cardview, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
            Pessoa p = professores.get(position);
            holder.itemView.setOnClickListener(
                    v -> {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(p.getSite()));
                        startActivity(i);
                    }
            );
            holder.nome.setText(p.getNome());
            holder.login.setText(p.getLogin());
            if (p.isLegal()) {
                holder.icone.setImageResource(R.drawable.ok);
            } else {
                holder.icone.setImageResource(R.drawable.delete);
            }
        }

        @Override
        public int getItemCount() {
            return professores.size();
        }
    }

    //ViewHolder
    //Cada instância de um objeto ViewHolder guarda uma representação visual dos dados exibidos
    class ProfessorViewHolder extends RecyclerView.ViewHolder {

        ImageView icone;
        TextView nome;
        TextView login;

        //o objeto do tipo View é um objeto que representa uma linha do RecyclerView (neste caso, R.layout.linha_professor)
        public ProfessorViewHolder(@NonNull View linha) {
            super(linha);
            this.icone = linha.findViewById(R.id.icone);
            this.nome = linha.findViewById(R.id.nome);
            this.login = linha.findViewById(R.id.login);
        }
    }
}