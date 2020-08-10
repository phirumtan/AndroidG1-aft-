package com.gsmarena.firstsample.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = User.class, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DB_NAME = "user_db";
    private static UserDatabase sUserDatabase;

    public static synchronized UserDatabase getInstance(Context context) {
        if (sUserDatabase == null)
            sUserDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        return sUserDatabase;
    }

    public abstract UserDao mUserDao();
}
