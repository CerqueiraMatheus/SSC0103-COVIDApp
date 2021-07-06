package com.poo.covidapp.Noticias;

import android.widget.AdapterView;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;

public interface NoticiasContract {

    interface View extends BaseView<NoticiasPresenter>, AdapterView.OnItemClickListener {
        void startNoticia();

        void setAdapter(NoticiasAdapter adapter);
    }

    interface Presenter extends BasePresenter {
        void onItemClick(int position);
    }
}
