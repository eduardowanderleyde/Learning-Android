package br.ufpe.cin.residencia.datamanagement.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


public class ProfessorRepository {
    private ProfessorDAO dao;
    public LiveData<List<Professor>> professores;

    public ProfessorRepository(ProfessorDAO professorDAO) {
        this.dao = professorDAO;
        this.professores = dao.professores();
    }

    @WorkerThread
    public void inserir(Professor p) {
        dao.inserir(p);
    }

    @WorkerThread
    public void atualizar(Professor p) {
        dao.atualizar(p);
    }

    @WorkerThread
    public void remover(Professor p) {
        dao.remover(p);
    }

    @WorkerThread
    List<Professor> listarTodosProfessores() {
        return dao.todosProfessores();
    }

    @WorkerThread
    Professor buscarPeloLogin(String login) {
        return dao.buscarPeloLogin(login);
    }

}