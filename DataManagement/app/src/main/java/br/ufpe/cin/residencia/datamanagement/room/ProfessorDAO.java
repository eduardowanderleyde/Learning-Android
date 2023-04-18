package br.ufpe.cin.residencia.datamanagement.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//DAO == Data Access Object
//quais operações o nosso banco de dados vai ter
// declarar o nome destas operações (a implementação vai ser gerada)
@Dao
public interface ProfessorDAO {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    void inserir(Professor p);

    @Update(onConflict=OnConflictStrategy.REPLACE)
    void atualizar(Professor p);

    @Delete
    void remover(Professor p);

    @Query("SELECT * FROM professores WHERE login = :loginProcurado")
    Professor buscarPeloLogin(String loginProcurado);

    @Query("SELECT * FROM professores ORDER BY nome ASC")
    List<Professor> todosProfessores();

    @Query("SELECT * FROM professores ORDER BY nome ASC")
    LiveData<List<Professor>> professores();

}
