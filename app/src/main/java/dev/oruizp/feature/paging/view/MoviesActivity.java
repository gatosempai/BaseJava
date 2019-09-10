package dev.oruizp.feature.paging.view;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import dev.oruizp.R;
import dev.oruizp.databinding.ActivityMoviesBinding;
import dev.oruizp.feature.paging.model.api.model.Movie;

public class MoviesActivity extends AppCompatActivity implements MoviesDataAdapter.ItemClickListener {

    private ActivityMoviesBinding binding;
    private MovieViewModel movieViewModel;
    private MoviesDataAdapter moviesDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_movies);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies);
        setUpViewModel();
        setUpRecyclerView();
        setUpSwipeRefresh();
        getMovies();
    }

    @Override
    public void onItemClickListener(Movie item) {
        //Intent intent=new Intent(this, MovieActivity.class);
        //intent.putExtra("movie", item);
        //startActivity(intent);
    }

    private void setUpViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    }

    private void setUpRecyclerView() {
        moviesDataAdapter = new MoviesDataAdapter(this);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rvMovies.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            binding.rvMovies.setLayoutManager(new GridLayoutManager(this, 4));
        }
        binding.rvMovies.setItemAnimator(new DefaultItemAnimator());
        binding.rvMovies.setAdapter(moviesDataAdapter);
    }

    private void setUpSwipeRefresh() {
        binding.swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });
    }

    private void getMovies() {
        movieViewModel.getMoviesPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                moviesDataAdapter.submitList(movies);
                moviesDataAdapter.notifyDataSetChanged();
                binding.swipeLayout.setRefreshing(false);
            }
        });
    }
}
