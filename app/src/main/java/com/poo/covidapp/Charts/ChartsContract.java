package com.poo.covidapp.Charts;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;
import com.poo.covidapp.Util.Models.Chart;

import java.util.TreeMap;

public interface ChartsContract {
    interface View extends BaseView<ChartsPresenter> {
        void startChartsActivity(String title, TreeMap<String, Float> entries);
        void setButtons(String[] titles, String[] descriptions);
        void onCasesClick(android.view.View view);
        void onCasesPer100kClick(android.view.View view);
        void onDeathsClick(android.view.View view);
    }

    interface Presenter extends BasePresenter {
        void requestChart(Chart.Types type);
    }
}
