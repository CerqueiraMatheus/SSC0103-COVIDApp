package com.poo.covidapp.News;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.poo.covidapp.Util.Models.News;

import java.util.ArrayList;
import java.util.List;

public class NewsPresenter implements NewsContract.Presenter {

    private final NewsContract.View view;
    private List<News> newsList;
    private Context context;

    // Presenter constructor
    public NewsPresenter(NewsContract.View view, Context context) {
        this.view = view;
        this.context = context;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        newsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            newsList.add(new News(
                    "Uma grande notícia",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam in orci porta, accumsan lacus eu, semper risus. Aenean fringilla orci in turpis sollicitudin, eu suscipit felis tincidunt. Donec eleifend nulla nec pulvinar posuere. Nulla faucibus dui nisi, eget rhoncus odio tempor ut. In auctor dolor id leo egestas mattis. Aliquam est quam, tristique at ipsum non, pretium facilisis libero. Curabitur consequat scelerisque tempus. ",
                    "CartaCapital",
                    "http://www.google.com"));
        }
        view.setAdapter(new NewsAdapter(newsList, view));
    }

    @Override
    // Handle the click
    public void onItemClick(int position) {
        view.startNews(new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).getUrl())));
    }
}
