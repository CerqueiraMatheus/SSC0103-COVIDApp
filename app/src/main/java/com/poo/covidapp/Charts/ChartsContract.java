package com.poo.covidapp.Charts;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;

public interface ChartsContract {
    interface View extends BaseView<ChartsPresenter> {
        void startChart();
        void setButtons(String[] titles, String[] descriptions);
    }

    interface Presenter extends BasePresenter {
        void requestChart();
    }
}
