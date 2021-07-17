package com.poo.covidapp.Charts;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;

public interface ChartsContract {
    interface View extends BaseView<ChartsPresenter> {
        void startChart();
    }

    interface Presenter extends BasePresenter {
        void createChart(int type);
    }
}
