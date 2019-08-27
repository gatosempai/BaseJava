package dev.oruizp.feature.paging.model.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dev.oruizp.feature.paging.model.api.MovieDataService;
import dev.oruizp.feature.paging.model.api.RestServiceFactory;
import dev.oruizp.feature.paging.model.api.model.Movie;
import dev.oruizp.feature.paging.model.api.model.MovieDBResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static String BASE_URL = "https://api.themoviedb.org/3/";

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Movie>> getMutableLiveData(String apiKey) {
        MovieDataService movieDataService = RestServiceFactory.createRetrofitService(MovieDataService.class, BASE_URL);
        Call<MovieDBResponse> call = movieDataService.getPopularMovies(apiKey);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Movie>> getMoviesWithPaging(String apiKey) {
        MovieDataService movieDataService = RestServiceFactory.createRetrofitService(MovieDataService.class, BASE_URL);
        Call<MovieDBResponse> call = movieDataService.getPopularMoviesWithPaging(apiKey, 1);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies;
                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
