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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.poo.covidapp.MainActivity;
import com.poo.covidapp.R;
import com.poo.covidapp.databinding.FragmentEstimationBinding;

import java.util.List;
import java.util.Objects;

public class EstimationFragment extends Fragment implements EstimationContract.View {

    private FragmentEstimationBinding binding;
    private EstimationPresenter presenter;
    private BottomNavigationView btnav;

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
        btnav = ((MainActivity) requireActivity()).findViewById(R.id.bottom_navigation);
    }

    @Override
    public void setPresenter(EstimationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showResult(String title, String body, Intent intent) {
        // Display the result inside an Alert Dialog
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
        // Set the state dropdown menu adapter with the states names
        binding.estimationState.setAdapter(
                new ArrayAdapter<>(requireContext(), R.layout.row_state, statesNames)
        );

        // Hide the bottom nav and keyboard on focus
        binding.estimationAge.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                btnav.setVisibility(View.GONE);
            } else {
                btnav.setVisibility(View.VISIBLE);
                hideKeyboard();
            }
        });

        // Show bottom nav again
        binding.estimationAge.setOnEditorActionListener((v, actionId, event) -> {
            btnav.setVisibility(View.VISIBLE);
            return false;
        });

        // Avoid soft keyboard appearing when focusing the dropdown menu
        binding.estimationState.setOnFocusChangeListener((v, hasFocus) -> hideKeyboard());

        // Link the button onClick with custom method
        binding.estimationSubmit.setOnClickListener(this::onSubmitClick);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0);
    }

    @Override
    public void onSubmitClick(View view) {
        // Restore visibilities
        btnav.setVisibility(View.VISIBLE);
        hideKeyboard();

        // Clear focuses
        binding.estimationAge.clearFocus();
        binding.estimationState.clearFocus();

        // Check if age is valid
        String age = Objects.requireNonNull(binding.estimationAge.getText()).toString();
        if (age.equals("") || Integer.parseInt(age) > 150) {
            binding.estimationAgeLayout.setError("Idade meio estranha...");
            return;
        }

        // Check if state is valid
        String state = Objects.requireNonNull(binding.estimationState.getText()).toString();
        if (state.equals("")) {
            binding.estimationStateLayout.setError("Selecione um estado!");
            return;
        }

        // Clean layout errors
        binding.estimationAgeLayout.setError("");
        binding.estimationStateLayout.setError("");

        // Submit
        presenter.onSubmit(Integer.parseInt(age), state, binding.estimationPni.isChecked());
    }
}