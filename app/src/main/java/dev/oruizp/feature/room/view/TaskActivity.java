package dev.oruizp.feature.room.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import dev.oruizp.R;
import dev.oruizp.databinding.ActivityTaskBinding;
import dev.oruizp.feature.room.model.db.AppDatabase;
import dev.oruizp.feature.room.model.db.TaskEntity;

public class TaskActivity extends AppCompatActivity implements TaskAdapter.ItemClickListener {

    private ActivityTaskBinding activityTaskBinding;
    private TaskAdapter taskAdapter;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_task);
        setUpRecyclerView();
        setUpDb();
        setUpViewModel();
    }

    @Override
    public void onItemClickListener(int itemId) {
        Intent intent = new Intent(this, TaskDetailActivity.class);
        intent.putExtra(TaskDetailActivity.EXTRA_TASK_ID, itemId);
        startActivity(intent);
    }

    private void setUpViewModel() {
        TaskViewModel taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getTasks().observe(this, new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(List<TaskEntity> taskEntities) {
                taskAdapter.setItemList(taskEntities);
            }
        });
    }

    private void setUpDb() {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
    }

    private void setUpRecyclerView() {
        taskAdapter = new TaskAdapter(this);
        activityTaskBinding.recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        activityTaskBinding.recyclerViewTasks.setAdapter(taskAdapter);
    }
}
