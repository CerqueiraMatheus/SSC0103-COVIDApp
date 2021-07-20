package com.poo.covidapp.About.Dict;

import com.poo.covidapp.About.AboutContract;

import java.util.ArrayList;
import java.util.List;

public class DictPresenter implements DictContract.Presenter{

    private DictContract.View view;

    public DictPresenter(DictContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        List<String> test = new ArrayList<String>();
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        test.add("e");

        view.populateView(test);
    }

    @Override
    public void onSelectWord(int position) {
        view.setWord(String.valueOf(position), "matheus é brabo");

    }

    @Override
    public void clearWord() {

    }
}
