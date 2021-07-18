package com.poo.covidapp.Estimation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.poo.covidapp.R;
import com.poo.covidapp.databinding.FragmentEstimationBinding;

import java.util.List;
import java.util.Objects;

public class EstimationFragment extends Fragment implements EstimationContract.View {

    private FragmentEstimationBinding binding;
    private EstimationPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEstimationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new EstimationPresenter(this, getContext());
        presenter.start();
    }

    @Override
    public void setPresenter(EstimationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showResult(String title, String body, Intent intent) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(body)
                .setNeutralButton("Acessar o site", (dialog, which) -> startActivity(intent))
                .setPositiveButton("Fechar", (dialog, which) -> {
                    binding.estimationAge.setText("");
                    binding.estimationState.setText("");
                    binding.estimationPni.setChecked(false);
                    dialog.dismiss();
                })
                .show();
    }

    @Override
    public void populateView(List<String> statesNames) {
        binding.estimationState.setAdapter(
                new ArrayAdapter<>(requireContext(), R.layout.row_state, statesNames)
        );
        binding.estimationState.setOnFocusChangeListener((v, hasFocus) -> {
            InputMethodManager imm = (InputMethodManager)
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0);
        });
        binding.estimationSubmit.setOnClickListener(this::onSubmitClick);
    }

    @Override
    public void onSubmitClick(View view) {
        String age = Objects.requireNonNull(binding.estimationAge.getText()).toString();
        if (age.equals("") || Integer.parseInt(age) > 150) {
            binding.estimationAgeLayout.setError("Idade meio estranha...");
            return;
        }

        String state = Objects.requireNonNull(binding.estimationState.getText()).toString();
        if (state.equals("")) {
            binding.estimationStateLayout.setError("Selecione um estado!");
            return;
        }

        binding.estimationAgeLayout.setError("");
        binding.estimationStateLayout.setError("");

        presenter.onSubmit(Integer.parseInt(age), state, binding.estimationPni.isChecked());
    }
}