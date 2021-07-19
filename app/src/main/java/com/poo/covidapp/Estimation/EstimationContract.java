package com.poo.covidapp.Estimation;

import android.content.Intent;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;

import java.util.List;

public interface EstimationContract {
    interface View extends BaseView<EstimationPresenter> {
        void showResult(String title, String body, Intent intent);

        void populateView(List<String> statesNames);

        void onSubmitClick(android.view.View view);
    }

    interface Presenter extends BasePresenter {
        void onSubmit(int age, String state, boolean isPNI);
    }
}