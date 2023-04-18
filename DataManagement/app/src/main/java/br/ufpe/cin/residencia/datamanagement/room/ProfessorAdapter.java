package br.ufpe.cin.residencia.datamanagement.room;

import static br.ufpe.cin.residencia.datamanagement.room.ProfessorDB.DB_NAME;

import br.ufpe.cin.residencia.datamanagement.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import androidx.room.Room;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorViewHolder> {
    LayoutInflater inflater;
    SortedList<Professor> professores;

    ProfessorAdapter(LayoutInflater layoutInflater) {
        this.inflater = layoutInflater;
        this.professores = new SortedList<>(
                Professor.class,
                new SortedList.Callback<Professor>() {
                    @Override
                    public int compare(Professor p1, Professor p2) {
                        return p1.login.compareTo(p2.login);
                    }

                    @Override
                    public boolean areContentsTheSame(Professor oldItem, Professor newItem) {
                        return oldItem.login.equals(newItem.login);
                    }

                    @Override
                    public boolean areItemsTheSame(Professor p1, Professor p2) {
                        return p1.login.equals(p2.login);
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

    public void atualizarProfessores(List<Professor> professors) {
        this.professores.clear();
        this.professores.addAll(professors);
        notifyDataSetChanged();
    }

    public void adicionarProfessor(Professor professor) {
        this.professores.add(professor);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfessorViewHolder(inflater.inflate(R.layout.linha_professor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor prof = professores.get(position);
        holder.nome.setText(prof.nome);
        holder.login.setText(prof.login);
        if (prof.legal) {
            holder.icone.setImageResource(R.drawable.ok);
        } else {
            holder.icone.setImageResource(R.drawable.delete);
        }
        holder.btnEdit.setOnClickListener(
                v -> {
                    Context c = holder.context;
                    Intent i = new Intent(c, EditarProfessorActivity.class);
                    i.putExtra("login", prof.login);
                    c.startActivity(i);
                    //startActivity(;

                }
        );
        holder.btnDelete.setOnClickListener(
                v -> {
                    new Thread(
                            () -> {
                                ProfessorDB db = ProfessorDB.getDB(holder.context);
                                ProfessorDAO dao = db.dao();
                                dao.remover(prof);
                            }
                    ).start();

                }
        );
    }

    @Override
    public int getItemCount() {
        return professores.size();
    }

}