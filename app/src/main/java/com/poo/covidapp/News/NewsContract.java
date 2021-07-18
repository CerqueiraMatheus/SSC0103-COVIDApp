package com.poo.covidapp.News;

import android.content.Intent;
import android.widget.AdapterView;

import com.poo.covidapp.Util.Base.BasePresenter;
import com.poo.covidapp.Util.Base.BaseView;

public interface NewsContract {

    interface View extends BaseView<NewsPresenter>, AdapterView.OnItemClickListener {
        void startNews(Intent intent);

        void setAdapter(NewsAdapter adapter);
    }

    interface Presenter extends BasePresenter {
        void onItemClick(int position);
    }
}
