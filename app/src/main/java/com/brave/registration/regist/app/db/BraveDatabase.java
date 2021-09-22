package com.brave.registration.regist.app.db;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.brave.registration.regist.app.db.dao.RoleDao;
import com.brave.registration.regist.app.db.dao.UserDao;
import com.brave.registration.regist.app.db.entities.Role;
import com.brave.registration.regist.app.db.entities.User;

@Database(entities = {User.class, Role.class}, version = 1)
public abstract class BraveDatabase extends RoomDatabase {

    private static BraveDatabase instance;

    public abstract UserDao userDao();
    public abstract RoleDao roleDao();

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
        private RoleDao roleDao;

        private PopulateDbAsyncTask(BraveDatabase db) {

            this.userDao = db.userDao();
            this.roleDao = db.roleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            long adminRoleID = roleDao.insert(new Role("ADMIN", 10));
            long moderatorRoleID = roleDao.insert(new Role("MODERATOR", 20));
            long volunteerRoleID = roleDao.insert(new Role("VOLUNTEER", 30));
            long heroRoleID = roleDao.insert(new Role("HERO", 40));

            User adminUser = new User("Admin", "admin@brave.com", "1212121212", adminRoleID);
            User moderatorUser = new User("Moderator", "moderator@brave.com", "2323232323", moderatorRoleID);
            User volunteerUser = new User("Volunteer", "volunteer@brave.com", "3434343434", volunteerRoleID);
            User heroUser = new User("Hero", "hero@brave.com", "4545454545", heroRoleID);

            userDao.insert(adminUser);
            userDao.insert(moderatorUser);
            userDao.insert(volunteerUser);
            userDao.insert(heroUser);
            return null;
        }
    }
}
