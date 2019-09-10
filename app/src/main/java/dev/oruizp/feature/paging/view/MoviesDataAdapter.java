package dev.oruizp.feature.paging.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import dev.oruizp.R;
import dev.oruizp.databinding.ItemMovieListBinding;
import dev.oruizp.feature.paging.model.api.model.Movie;

public class MoviesDataAdapter extends PagedListAdapter<Movie, MoviesDataAdapter.MoviesViewHolder> {

    private ItemClickListener itemClickListener;

    public MoviesDataAdapter(ItemClickListener itemClickListener) {
        super(Movie.CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //return new MoviesViewHolder(ItemMovieListBinding.inflate(layoutInflater, parent, false));
        ItemMovieListBinding itemMovieListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie_list, parent, false);
        return new MoviesViewHolder(itemMovieListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.bindView(getItem(position));
    }

    public interface ItemClickListener {
        void onItemClickListener(Movie item);
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieListBinding itemView;

        MoviesViewHolder(@NonNull ItemMovieListBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        void bindView(final Movie item) {
            // TODO improve the posterPath format
            String imagePath = "https://image.tmdb.org/t/p/w500" + item.getPosterPath();
            item.setFinalPosterPath(imagePath);
            itemView.setMovie(item);
            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClickListener(item);
                    }
                }
            });
        }
    }
}
