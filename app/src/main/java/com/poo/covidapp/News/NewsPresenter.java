package com.poo.covidapp.News;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.poo.covidapp.R;
import com.poo.covidapp.Util.Models.News;
import java.util.ArrayList;
import java.util.List;

public class NewsPresenter implements NewsContract.Presenter {

    private final NewsContract.View view;
    private List<News> newsList;
    private Context context;
    NewsApiClient newsApiClient;


    // Presenter constructor
    public NewsPresenter(NewsContract.View view, Context context) {
        this.view = view;
        this.context = context;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        newsList = new ArrayList<>();

        newsApiClient = new NewsApiClient(context.getString(R.string.NEWS_API_KEY));

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder().q("covid").country("br").language("pt").build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        for (Article article : response.getArticles()) {
                            newsList.add(new News(
                                    article.getTitle(),
                                    article.getDescription(),
                                    article.getSource().getName(),
                                    article.getUrl(),
                                    article.getUrlToImage()
                            ));
                        }
                        view.setAdapter(new NewsAdapter(newsList, view));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        newsList.add(new News(
                                "Erro ao resgatar notícias",
                                "Infelizmente não conseguimos resgatar nenhuma notícia do nosso servidor.\n" +
                                        "Favor contatar os publicadores do aplicativo e informar o erro.\n\n" +
                                        "Erro: " + throwable.getMessage(),
                                "Time CovidApp",
                                "https://github.com/CerqueiraMatheus/SSC0103-COVIDApp",
                                "https://www.pinclipart.com/picdir/middle/576-5766852_frowning-face-emoji-clipart-smiley-png-download.png"
                        ));
                        view.setAdapter(new NewsAdapter(newsList, view));
                    }
                }
        );


    }

    @Override
    // Handle the click
    public void onItemClick(int position) {
        view.startNews(new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).getUrl())));
    }
}
