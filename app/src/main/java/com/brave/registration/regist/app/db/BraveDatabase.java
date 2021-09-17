package com.brave.registration.regist.app.db;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.brave.registration.regist.app.db.dao.UserDao;
import com.brave.registration.regist.app.db.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class BraveDatabase extends RoomDatabase {

    private static BraveDatabase instance;

    public abstract UserDao userDao();

    public static synchronized BraveDatabase getInstance(Context context) {
        if ( instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BraveDatabase.class, "brave_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private PopulateDbAsyncTask(BraveDatabase db) {
            this.userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User ("Super", "User", "", ""));
            return null;
        }
    }
}
