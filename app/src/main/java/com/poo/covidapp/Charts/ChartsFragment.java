package com.poo.covidapp.Charts;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poo.covidapp.R;
import com.poo.covidapp.databinding.FragmentChartsBinding;


import java.util.Map;

public class ChartsFragment extends Fragment implements ChartsContract.View, View.OnClickListener {
    private ChartsPresenter presenter;
    private FragmentChartsBinding binding;

    public ChartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChartsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new ChartsPresenter(this);
        presenter.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                System.out.println("Click no Botao 1!");
                break;
            case R.id.button2:
                System.out.println("Click no Botao 2!");
                break;
            case R.id.button3:
                System.out.println("Click no Botao 3!");
                break;
        }
    }

    @Override
    public void startChart() {
        Intent intent = new Intent(getActivity(), ChartActivity.class);
        intent.putExtra("", "");
        startActivity(intent);
    }

    @Override
    public void setPresenter(ChartsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}