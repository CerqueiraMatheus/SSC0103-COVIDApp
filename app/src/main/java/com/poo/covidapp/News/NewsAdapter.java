package com.poo.covidapp.News;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.poo.covidapp.Util.Models.News;
import com.poo.covidapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomViewHolder> {

    private final List<News> newsList;
    private final AdapterView.OnItemClickListener onItemClickListener;

    NewsAdapter(List<News> dataList, AdapterView.OnItemClickListener listener) {
        this.newsList = dataList;
        this.onItemClickListener = listener;
    }

    // Custom ViewHolder for each element
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final View mView;
        TextView title;
        TextView body;
        TextView published;
        CardView cardView;
        ImageView image;

        // Link Views and OnClickListeners
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            title = mView.findViewById(R.id.row_noticia_titulo);
            body = mView.findViewById(R.id.row_noticia_corpo);
            published = mView.findViewById(R.id.row_noticia_publicadopor);
            image = mView.findViewById(R.id.row_noticia_img);
            cardView = mView.findViewById(R.id.row_noticia_card);
            itemView.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(null, v, getAdapterPosition(), v.getId());
        }
    }

    @NonNull
    @Override
    // Inflate the element inside the View
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_news, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    // Populate the element
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.title.setText(newsList.get(position).getTitle());
        holder.body.setText(newsList.get(position).getBody());
        holder.published.setText(newsList.get(position).getPublished());
        Picasso.get().load(newsList.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}