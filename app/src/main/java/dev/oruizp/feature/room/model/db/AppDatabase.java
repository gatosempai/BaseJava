package dev.oruizp.feature.room.model.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {TaskEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "appDB";
    private static AppDatabase dbIntance;

    public static AppDatabase getInstance(Context context) {
        if (dbIntance != null) return dbIntance;
        synchronized (LOCK) {
            Log.d(LOG_TAG, "Creating new database instance");
            dbIntance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, AppDatabase.DATABASE_NAME)
                    .build();
            return dbIntance;
        }
    }

    public abstract TaskDao taskDao();
}
