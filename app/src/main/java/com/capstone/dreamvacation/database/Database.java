package com.capstone.dreamvacation.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.capstone.dreamvacation.dao.ExcursionDAO;
import com.capstone.dreamvacation.dao.VacationDAO;
import com.capstone.dreamvacation.entities.Excursion;
import com.capstone.dreamvacation.entities.Vacation;

@androidx.room.Database(entities= {Vacation.class, Excursion.class}, version= 2,exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract VacationDAO vacationDAO();

    public abstract ExcursionDAO excursionDAO();

    private static volatile Database INSTANCE;

    static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "MyDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

