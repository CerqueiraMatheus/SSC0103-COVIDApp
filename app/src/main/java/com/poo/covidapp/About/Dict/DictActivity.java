package com.poo.covidapp.About.Dict;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.poo.covidapp.About.AboutPresenter;
import com.poo.covidapp.R;
import com.poo.covidapp.databinding.ActivityChartsBinding;
import com.poo.covidapp.databinding.ActivityDictBinding;

import java.util.List;

public class DictActivity extends AppCompatActivity implements DictContract.View {

    private ActivityDictBinding binding;
    private DictPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set view and binding
        setContentView(R.layout.activity_dict);
        binding = ActivityDictBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Set and start presenter
        new DictPresenter(this, getApplicationContext());
        presenter.start();

        // Change action bar and title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Expressões da pandemia");
    }

    @Override
    public void setPresenter(DictPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void populateView(List<String> strings) {
        // Set the state dropdown menu adapter with the states names
        binding.dictSearch.setAdapter(
                new ArrayAdapter<>(getBaseContext(), R.layout.row_state, strings)
        );

        // On item click dict search
        binding.dictSearch.setOnItemClickListener((parent, view, position, id) -> {

            // Hide keyboard
            InputMethodManager imm = (InputMethodManager)
                    this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);

            // Call on selected word
            presenter.onSelectWord((String) ((TextView) view).getText());

            // Reset interfaces
            binding.dictSearch.setText("");
            getWindow().getDecorView().clearFocus();
        });

        binding.dictSearch.requestFocus();
    }

    @Override
    public void setWord(String title, String body) {
        binding.dictTitle.setText(title);
        binding.dictBody.setText(body);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}