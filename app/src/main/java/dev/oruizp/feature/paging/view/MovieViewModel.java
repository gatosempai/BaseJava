package dev.oruizp.feature.paging.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.oruizp.feature.paging.model.api.model.Movie;
import dev.oruizp.feature.paging.model.repository.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private static String API_KEY = "b133c1180758351bf9a6631f15a7457f";
    LiveData<MovieDataSource> movieDataSourceLiveData;
    private MovieRepository movieRepository;
    private LiveData<PagedList<Movie>> moviesPagedList;
    private Executor executor;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(movieRepository, API_KEY);
        movieDataSourceLiveData = movieDataSourceFactory.getMovieDataSourceMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        moviesPagedList = (new LivePagedListBuilder<Long, Movie>(movieDataSourceFactory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData(API_KEY);
    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
