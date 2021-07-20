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

public class DictActivity extends AppCompatActivity implements DictContract.View{

    private ActivityDictBinding binding;
    private DictPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict);
        binding = ActivityDictBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new DictPresenter(this);
        presenter.start();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Dicionário Pandemia");
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

        // Avoid soft keyboard appearing when focusing the dropdown menu
        binding.dictSearch.setOnFocusChangeListener((v, hasFocus) -> {
            InputMethodManager imm = (InputMethodManager)
                    this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
        });

        binding.dictSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onSelectWord(position);
                System.out.println(position);
            }
        });

        binding.dictSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // presenter.onSelectWord(((TextView) view).getText());
                presenter.onSelectWord(position);
                System.out.println(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                presenter.clearWord();
            }
        });
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