package com.poo.covidapp.About.Dict;

import android.content.Context;
import android.content.res.Resources;

import com.poo.covidapp.About.AboutContract;
import com.poo.covidapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DictPresenter implements DictContract.Presenter {

    private DictContract.View view;
    private Context context;

    public DictPresenter(DictContract.View view, Context context) {
        this.view = view;
        this.context = context;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        // Get and convert titles
        String[] titles = context.getResources().getString(R.string.titles).split(",");
        List<String> terms = new ArrayList<>(Arrays.asList(titles));

        // Populate
        view.populateView(terms);
    }

    @Override
    public void onSelectWord(String title) {
        // Get description from title
        String name = title.toLowerCase()
                .replace(' ', '_')
                .replace('-', '_')
                .replace('(', '_')
                .replace(')', '_');
        Resources res = context.getResources();
        String definition = context.getResources()
                .getString(res.getIdentifier(name, "string", context.getPackageName()));

        // Set word with definition
        view.setWord(title, definition);
    }
}
