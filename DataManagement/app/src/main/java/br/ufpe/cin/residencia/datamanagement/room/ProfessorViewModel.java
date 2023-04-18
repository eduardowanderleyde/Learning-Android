package br.ufpe.cin.residencia.datamanagement.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ProfessorViewModel extends AndroidViewModel {
    private ProfessorRepository repository;

    private MutableLiveData<Professor> _professorAtual = new MutableLiveData<>();
    public LiveData<Professor> professorAtual = _professorAtual;


    public LiveData<List<Professor>> professores;

    public ProfessorViewModel(@NonNull Application app) {
        super(app);
        this.repository = new ProfessorRepository(ProfessorDB.getDB(app).dao());
        this.professores = this.repository.professores;
    }

    public void inserir(Professor p) {
        rodarEmBackground(
                () -> this.repository.inserir(p)
        );
    }

    public void atualizar(Professor p) {
        rodarEmBackground(
                () -> this.repository.atualizar(p)
        );
    }

    public void remover(Professor p) {
        rodarEmBackground(
                () -> this.repository.remover(p)
        );
    }

    void buscarPeloLogin(String login) {
        rodarEmBackground(
                () -> {
                    Professor p = this.repository.buscarPeloLogin(login);
                    //o que fazer com este professor?
                    _professorAtual.postValue(p);
                }
        );

    }

    private void rodarEmBackground(Runnable r) {
        new Thread(r).start();
    }

}
