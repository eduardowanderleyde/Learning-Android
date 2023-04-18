package br.ufpe.cin.residencia.datamanagement.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Professor.class}, version = 1)
public abstract class ProfessorDB extends RoomDatabase {
    public abstract ProfessorDAO dao();

    public static final String DB_NAME = "professors.db";

    private static volatile ProfessorDB INSTANCE;

    synchronized static ProfessorDB getDB(Context c) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    c,
                    ProfessorDB.class,
                    DB_NAME
            ).build();
        }
        return INSTANCE;
    }
}
