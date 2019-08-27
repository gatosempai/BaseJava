package dev.oruizp.feature.paging.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import dev.oruizp.feature.paging.model.api.model.Movie;
import dev.oruizp.feature.paging.model.repository.MovieRepository;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private MovieRepository movieRepository;
    private String apiKey;
    private MutableLiveData<List<Movie>> listMutableLiveData;

    public MovieDataSource(MovieRepository movieRepository, String apiKey) {
        this.movieRepository = movieRepository;
        this.apiKey = apiKey;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {
        listMutableLiveData = movieRepository.getMoviesWithPaging(apiKey);
        listMutableLiveData.observeForever(new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                callback.onResult(movies, null, 2L);
                listMutableLiveData.removeObserver(this);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {
        listMutableLiveData = movieRepository.getMoviesWithPaging(apiKey);
        listMutableLiveData.observeForever(new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                callback.onResult(movies, params.key + 1);
                listMutableLiveData.removeObserver(this);
            }
        });
    }
}
