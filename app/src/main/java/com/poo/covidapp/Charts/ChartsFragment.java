package com.poo.covidapp.Charts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.poo.covidapp.Util.Models.Chart;
import com.poo.covidapp.databinding.FragmentChartsBinding;

import java.util.TreeMap;

public class ChartsFragment extends Fragment implements ChartsContract.View {
    private ChartsPresenter presenter;
    private FragmentChartsBinding binding;

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

        // Set and start presenter
        new ChartsPresenter(this, getContext());
        presenter.start();
    }

    @Override
    public void startChartsActivity(String title, TreeMap<String, Float> entries) {
        Intent intent = new Intent(getActivity(), ChartsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("entries", entries);
        startActivity(intent);
    }

    @Override
    public void setPresenter(ChartsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setButtons(String[] titles, String[] descriptions) {
        // Set button 1
        binding.chartsButtonCases.rowOptionTitle.setText(
                titles[Chart.Types.CASES.ordinal()]);
        binding.chartsButtonCases.rowOptionDescription.setText(
                descriptions[Chart.Types.CASES.ordinal()]);
        binding.chartsButtonCases.rowOption.setOnClickListener(this::onCasesClick);

        // Set button 2
        binding.chartsButtonCasesPer100k.rowOptionTitle.setText(
                titles[Chart.Types.CASES_PER_100K.ordinal()]);
        binding.chartsButtonCasesPer100k.rowOptionDescription.setText(
                descriptions[Chart.Types.CASES_PER_100K.ordinal()]);
        binding.chartsButtonCasesPer100k.rowOption.setOnClickListener(this::onCasesPer100kClick);

        // Set button 3
        binding.chartsButtonDeaths.rowOptionTitle.setText(
                titles[Chart.Types.DEATHS.ordinal()]);
        binding.chartsButtonDeaths.rowOptionDescription.setText(
                descriptions[Chart.Types.DEATHS.ordinal()]);
        binding.chartsButtonDeaths.rowOption.setOnClickListener(this::onDeathsClick);
    }

    @Override
    public void onCasesClick(View view) {
        presenter.requestChart(Chart.Types.CASES);
    }

    @Override
    public void onCasesPer100kClick(View view) {
        presenter.requestChart(Chart.Types.CASES_PER_100K);
    }

    @Override
    public void onDeathsClick(View view) {
        presenter.requestChart(Chart.Types.DEATHS);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
