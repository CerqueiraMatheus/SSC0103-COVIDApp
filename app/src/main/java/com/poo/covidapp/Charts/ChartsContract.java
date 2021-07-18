package com.poo.covidapp.Charts;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;
import com.poo.covidapp.Util.Modelo.Chart;

import java.util.ArrayList;

public interface ChartsContract {
    interface View extends BaseView<ChartsPresenter> {
        void startChartsActivity(ArrayList<String> initials, ArrayList<Float> values);
        void setButtons(String[] titles, String[] descriptions);
    }

    interface Presenter extends BasePresenter {
        void requestChart(Chart.Types type);
    }
}
