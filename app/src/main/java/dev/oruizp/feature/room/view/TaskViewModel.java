package dev.oruizp.feature.room.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import dev.oruizp.feature.room.model.db.TaskEntity;
import dev.oruizp.feature.room.model.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository taskRepository;
    private LiveData<List<TaskEntity>> tasks;
    private LiveData<TaskEntity> task;

    public TaskViewModel(Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
    }

    public LiveData<List<TaskEntity>> getTasks() {
        tasks = taskRepository.getTasks();
        return tasks;
    }

    public LiveData<TaskEntity> getTask(int id) {
        task = taskRepository.getTaskById(id);
        return task;
    }

    public void insertTask(String description, int priority, Date date) {
        TaskEntity taskEntity = new TaskEntity(description, priority, date);
        taskRepository.insertTask(taskEntity);
    }

    public void updateTask(int id, String description, int priority, Date date) {
        TaskEntity taskEntity = new TaskEntity(id, description, priority, date);
        taskRepository.updateTask(taskEntity);
    }

    public void deleteTask(String description, int priority, Date date) {
        TaskEntity taskEntity = new TaskEntity(description, priority, date);
        taskRepository.deleteTask(taskEntity);
    }

}
