package dev.oruizp.feature.landing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.oruizp.R;

public class LandingActivity extends AppCompatActivity implements LandingDataAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private LandingDataAdapter landingDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecyclerView();
    }

    @Override
    public void onItemClickListener(LandingData.Feature feature) {
        Toast.makeText(this, feature.name(), Toast.LENGTH_SHORT).show();
        goToFeature(feature);
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewLandingFeatures);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        landingDataAdapter = new LandingDataAdapter(fillList(), this);
        recyclerView.setAdapter(landingDataAdapter);
    }

    private void goToFeature(LandingData.Feature feature) {
        Intent launchIntent = new Intent();

    }

    private List<LandingData> fillList() {
        ArrayList<LandingData> list = new ArrayList<>();
        list.add(new LandingData("How to use Paging", LandingData.Feature.PAGING));
        list.add(new LandingData("How to use Room", LandingData.Feature.ROOM));
        list.add(new LandingData("How to use LiveData", LandingData.Feature.LIVEDATA));
        list.add(new LandingData("How to use Broadcast", LandingData.Feature.BROADCAST));
        list.add(new LandingData("How to use Service", LandingData.Feature.SERVICE));
        return list;
    }
}
