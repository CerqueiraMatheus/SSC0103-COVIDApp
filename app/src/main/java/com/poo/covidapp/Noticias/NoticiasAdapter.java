package com.poo.covidapp.Noticias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.poo.covidapp.Util.Modelo.Noticia;
import com.poo.covidapp.R;

import java.util.List;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.CustomViewHolder> {

    private List<Noticia> listaNoticia;
    private AdapterView.OnItemClickListener onItemClickListener;

    NoticiasAdapter(List<Noticia> dataList, AdapterView.OnItemClickListener listener) {
        this.listaNoticia = dataList;
        this.onItemClickListener = listener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final View mView;
        TextView titulo;
        TextView corpo;
        TextView publicadaPor;
        CardView cardView;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            titulo = mView.findViewById(R.id.row_noticia_titulo);
            corpo = mView.findViewById(R.id.row_noticia_corpo);
            publicadaPor = mView.findViewById(R.id.row_noticia_publicadopor);
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
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_noticia, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.titulo.setText(listaNoticia.get(position).getTitulo());
        holder.corpo.setText(listaNoticia.get(position).getResumo());
        holder.publicadaPor.setText(listaNoticia.get(position).getPublicadaPor());
    }

    @Override
    public int getItemCount() {
        return listaNoticia.size();
    }
}