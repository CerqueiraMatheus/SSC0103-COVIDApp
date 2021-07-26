package com.poo.covidapp.About.Dict;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;

import java.util.List;

public interface DictContract {
    interface View extends BaseView<DictPresenter> {
        void populateView(List<String> strings);
        void setWord(String title, String body);

    }

    interface Presenter extends BasePresenter {
        void onSelectWord(String title);
    }
}
