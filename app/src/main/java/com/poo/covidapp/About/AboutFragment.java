package com.poo.covidapp.About;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.poo.covidapp.About.Dict.DictActivity;
import com.poo.covidapp.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment implements AboutContract.View {

    private FragmentAboutBinding binding;
    private AboutPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set and start presenter
        new AboutPresenter(this);
        presenter.start();
    }

    @Override
    public void populateView(String[] titles, String[] descriptions) {
        // Set button 1
        binding.aboutDictionary.rowOptionTitle.setText(titles[0]);
        binding.aboutDictionary.rowOptionDescription.setText(descriptions[0]);
        binding.aboutDictionary.rowOption.setOnClickListener(this::onDictClick);

        // Set button 2
        binding.aboutProject.rowOptionTitle.setText(titles[1]);
        binding.aboutProject.rowOptionDescription.setText(descriptions[1]);
        binding.aboutProject.rowOption.setOnClickListener(this::onProjectClick);

        // Set button 3
        binding.aboutAuthors.rowOptionTitle.setText(titles[2]);
        binding.aboutAuthors.rowOptionDescription.setText(descriptions[2]);
        binding.aboutAuthors.rowOption.setOnClickListener(this::onAuthorsClick);
    }


    @Override
    public void onDictClick(View view) {
        Intent intent = new Intent(requireActivity(), DictActivity.class);
        startActivity(intent);
    }

    @Override
    public void onProjectClick(View view) {
        Intent intent = new Intent(requireActivity(), AboutStaticActivity.class);
        intent.putExtra("type", 0);
        startActivity(intent);
    }

    @Override
    public void onAuthorsClick(View view) {
        Intent intent = new Intent(requireActivity(), AboutStaticActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }

    @Override
    public void setPresenter(AboutPresenter presenter) {
        this.presenter = presenter;
    }
}