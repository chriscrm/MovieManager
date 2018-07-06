package sv.com.hometech.moviemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sv.com.hometech.moviemanager.R;
import sv.com.hometech.moviemanager.activities.MovieDetailActivity;
import sv.com.hometech.moviemanager.models.Movie;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    List<Movie> movies;
    Context context;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies){
        this.movies = movies;
        this.context = context;
    }

    private Context getContext(){
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Movie movie = movies.get(position);
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        Picasso.with(getContext())
                .load(movie.getPosterPath())
                .into(viewHolder.idMovieImage);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.idMovieImage)
        ImageView idMovieImage;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvOverview)
        TextView tvOverview;
        @BindView(R.id.cvMovie)
        CardView cvMovie;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Movie movie = movies.get(getAdapterPosition());

            Intent intent = new Intent(getContext(), MovieDetailActivity.class);
            intent.putExtra("MOVIE", movie);
            getContext().startActivity(intent);

        }
    }
}
