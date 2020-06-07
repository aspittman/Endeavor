package com.affinityapps.endeavor.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Form.class}, version = 1)
public abstract class FormDatabase extends RoomDatabase {

    private static FormDatabase instance;

    public abstract FormDao formDao();

    public static synchronized FormDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FormDatabase.class, "form_database").
                    fallbackToDestructiveMigration().
                    addCallback(roomCallback).
                    build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(instance).execute();
        }
    };

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private FormDao formDao;

        private PopulateDatabaseAsyncTask(FormDatabase formDatabase) {
            formDao = formDatabase.formDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            formDao.insert(new Form(1, "test", "test", "test", 1, 1.2, 1.3, 1.4));
            formDao.insert(new Form(1, "test", "test", "test", 2, 1.2, 1.3, 1.4));
            formDao.insert(new Form(1, "test", "test", "test", 3, 1.2, 1.3, 1.4));
            return null;
        }
    }


}
