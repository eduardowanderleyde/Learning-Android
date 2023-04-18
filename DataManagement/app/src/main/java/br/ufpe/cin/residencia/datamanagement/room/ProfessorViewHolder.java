package br.ufpe.cin.residencia.datamanagement.room;

import br.ufpe.cin.residencia.datamanagement.R;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfessorViewHolder extends RecyclerView.ViewHolder {
    TextView nome = null;
    TextView login = null;
    ImageView icone = null;
    ImageView btnEdit = null;
    ImageView btnDelete = null;
    Context context = null;

    public ProfessorViewHolder(@NonNull View linha) {
        super(linha);
        this.nome = linha.findViewById(R.id.nome);
        this.login = linha.findViewById(R.id.login);
        this.icone = linha.findViewById(R.id.icone);
        this.btnEdit = linha.findViewById(R.id.btn_edit);
        this.btnDelete = linha.findViewById(R.id.btn_delete);
        this.context = linha.getContext();
    }
}
