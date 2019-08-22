package dev.oruizp.feature.room.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

public class TaskActivity extends AppCompatActivity implements TaskAdapter.ItemClickListener,
        View.OnClickListener {

    private ActivityTaskBinding binding;
    private TaskAdapter taskAdapter;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task);
        setUpRecyclerView();
        setUpDb();
        setUpViewModel();
        setUpView();
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
        binding.recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewTasks.setAdapter(taskAdapter);
    }

    private void setUpView() {
        binding.fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, TaskDetailActivity.class);
        startActivity(intent);
    }
}
