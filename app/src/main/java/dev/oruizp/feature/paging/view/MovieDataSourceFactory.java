package dev.oruizp.feature.paging.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import dev.oruizp.feature.paging.model.repository.MovieRepository;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;
    private MovieRepository movieRepository;
    private String apiKey;

    public MovieDataSourceFactory(MovieRepository movieRepository, String apiKey) {
        this.movieRepository = movieRepository;
        this.apiKey = apiKey;
        movieDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(movieRepository, apiKey);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
