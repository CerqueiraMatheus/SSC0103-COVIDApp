package com.poo.covidapp.News;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poo.covidapp.databinding.FragmentNewsBinding;


public class NewsFragment extends Fragment implements NewsContract.View {

    private NewsPresenter presenter;
    private FragmentNewsBinding binding;

    @Override
    // Bind the View and initiate the presenter
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        new NewsPresenter(this, requireContext());
        presenter.start();
        return binding.getRoot();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClick(position);
    }

    @Override
    public void startNews(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void setAdapter(NewsAdapter adapter) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.listaNoticia.setLayoutManager(layoutManager);
        binding.listaNoticia.setAdapter(adapter);
    }

    @Override
    public void setPresenter(NewsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}