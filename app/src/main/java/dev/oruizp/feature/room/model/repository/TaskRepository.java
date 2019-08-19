package dev.oruizp.feature.room.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.oruizp.feature.room.model.db.AppDatabase;
import dev.oruizp.feature.room.model.db.TaskDao;
import dev.oruizp.feature.room.model.db.TaskEntity;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<TaskEntity>> tasks;
    private LiveData<TaskEntity> task;

    public TaskRepository(Application application) {
        taskDao = AppDatabase.getInstance(application).taskDao();
    }

    public LiveData<List<TaskEntity>> getTasks() {
        return taskDao.loadAllTask();
    }

    public LiveData<TaskEntity> getTaskById(int id) {
        return taskDao.loadTaskById(id);
    }

    public void insertTask(final TaskEntity taskEntity) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.insertTask(taskEntity);
            }
        });
    }

    public void updateTask(final TaskEntity taskEntity) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.updateTask(taskEntity);
            }
        });
    }

    public void deleteTask(final TaskEntity taskEntity) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.deleteTask(taskEntity);
            }
        });
    }
}
