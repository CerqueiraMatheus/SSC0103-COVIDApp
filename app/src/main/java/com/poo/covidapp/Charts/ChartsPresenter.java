package com.poo.covidapp.Charts;

public class ChartsPresenter implements ChartsContract.Presenter {
    private ChartsContract.View view;

    public ChartsPresenter(ChartsContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void createChart(int type) {

    }
}
