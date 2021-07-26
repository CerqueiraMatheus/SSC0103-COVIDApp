package com.poo.covidapp.About;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;

public interface AboutContract {
    interface View extends BaseView<AboutPresenter> {
        void populateView(String[] titles, String[] descriptions);

        void onProjectClick(android.view.View view);

        void onDictClick(android.view.View view);

        void onAuthorsClick(android.view.View view);
    }

    interface Presenter extends BasePresenter {
    }
}
