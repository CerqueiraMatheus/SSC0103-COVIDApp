package com.poo.covidapp.Charts;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poo.covidapp.R;
import com.poo.covidapp.databinding.FragmentChartsBinding;

public class ChartsFragment extends Fragment implements ChartsContract.View, View.OnClickListener {
    private ChartsPresenter presenter;
    private FragmentChartsBinding binding;


    /* Create */

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
        new ChartsPresenter(this, getContext());
        presenter.start();
    }


    /*  */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCases:
                System.out.println("Click in Cases!");
                presenter.requestChart();
                break;
            case R.id.buttonCasesPer100k:
                System.out.println("Click in Cases per 100k!");
                break;
            case R.id.buttonDeaths:
                System.out.println("Click in Deaths!");
                break;
        }
    }

    @Override
    public void startChart() {
        Intent intent = new Intent(getActivity(), ChartActivity.class);
        intent.putExtra("", "");
        startActivity(intent);
    }


    /* Getters and Setters */

    @Override
    public void setPresenter(ChartsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setButtons(String[] titles, String[] descriptions) {
        binding.buttonCases.setText(titles[0]);
        // binding.buttonCases.setDescription(descriptions[0]);
        binding.buttonCases.setOnClickListener(this);

        binding.buttonCasesPer100k.setText(titles[1]);
        // binding.buttonCasesPer100k.setDescription(descriptions[1]);
        binding.buttonCasesPer100k.setOnClickListener(this);

        binding.buttonDeaths.setText(titles[2]);
        // binding.buttonDeaths.setDescription(descriptions[2]);
        binding.buttonDeaths.setOnClickListener(this);
    }


    /* Destroy */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}