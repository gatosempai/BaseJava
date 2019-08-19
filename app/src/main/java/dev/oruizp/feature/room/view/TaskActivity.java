package dev.oruizp.feature.room.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.oruizp.R;
import dev.oruizp.databinding.ActivityTaskBinding;
import dev.oruizp.feature.room.model.db.AppDatabase;

public class TaskActivity extends AppCompatActivity {

    private ActivityTaskBinding activityTaskBinding;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_task);
        activityTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_task);
        setUpRecyclerView();
        setUpDb();
        setUpViewModel();
    }

    private void setUpViewModel() {
        TaskViewModel taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

    }

    private void setUpDb() {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
    }

    private void setUpRecyclerView() {
        activityTaskBinding.recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

    }
}
