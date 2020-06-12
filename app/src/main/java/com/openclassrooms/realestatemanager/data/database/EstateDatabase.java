package com.openclassrooms.realestatemanager.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.model.Image;


@Database(entities = {Estate.class, Image.class}, version = 3)
public abstract class EstateDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static EstateDatabase INSTANCE;

    // --- DAO ---
    public abstract EstateDao estateDao();
    public abstract ImageDao imageDao();

    // --- INSTANCE ---
    public static synchronized EstateDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    EstateDatabase.class, "estate_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private EstateDao estateDao;

        private PopulateDbAsyncTask(EstateDatabase db) { estateDao = db.estateDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            estateDao.insert(new Estate("Image 1", "Type 1", "100000", "Address 1", "Surface 1", "Agent 1", "EntryDate 1"));
            estateDao.insert(new Estate("Image 2", "Type 2", "200000", "Address 2", "Surface 2", "Agent 2", "EntryDate 2"));
            estateDao.insert(new Estate("Image 3", "Type 3", "300000", "Address 3", "Surface 3", "Agent 3", "EntryDate 3"));
            return null;
        }
    }
}

