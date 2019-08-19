package dev.oruizp.feature.room.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.oruizp.feature.room.model.db.TaskEntity;
import dev.oruizp.feature.room.model.repository.TaskRepository;

public class TaskViewModel extends ViewModel {

    private TaskRepository taskRepository;
    private LiveData<List<TaskEntity>> tasks;
    private LiveData<TaskEntity> task;


}
