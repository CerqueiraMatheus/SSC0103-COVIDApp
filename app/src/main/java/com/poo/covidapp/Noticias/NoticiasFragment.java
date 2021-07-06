package com.poo.covidapp.Noticias;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poo.covidapp.databinding.FragmentNoticiasBinding;

public class NoticiasFragment extends Fragment implements NoticiasContract.View {

    private NoticiasPresenter presenter;
    private FragmentNoticiasBinding binding;

    public NoticiasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //        return inflater.inflate(R.layout.fragment_noticias, container, false);

        binding = FragmentNoticiasBinding.inflate(inflater, container, false);
        new NoticiasPresenter(this);
        presenter.start();
        System.out.println("Executou");
        return binding.getRoot();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClick(position);
    }

    @Override
    public void startNoticia(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void setAdapter(NoticiasAdapter adapter) {
        System.out.println("chegou!!!");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.listaNoticia.setLayoutManager(layoutManager);
        binding.listaNoticia.setAdapter(adapter);
    }

    @Override
    public void setPresenter(NoticiasPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}