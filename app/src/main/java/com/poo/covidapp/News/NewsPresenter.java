package com.poo.covidapp.News;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.poo.covidapp.R;
import com.poo.covidapp.Util.Models.News;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        // Set presenter
        view.setPresenter(this);
    }

    @Override
    public void start() {
        // Create new array list
        newsList = new ArrayList<>();

        // Get instance of News API
        newsApiClient = new NewsApiClient(context.getString(R.string.NEWS_API_KEY));

        // Set client
        newsApiClient.getTopHeadlines(
                // Set parameters
                new TopHeadlinesRequest.Builder()
                        .q("covid")
                        .country("br")
                        .language("pt")
                        .pageSize(25)
                        .build(),
                // Set custom callback
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        // Format date
                        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM', às' HH:mm");
                        LocalDateTime date;

                        // Get each article
                        for (Article article : response.getArticles()) {

                            // Format data
                            String dt = article.getPublishedAt();
                            date = LocalDateTime.parse(dt.substring(0, dt.length() - 1));

                            // Add to list
                            newsList.add(new News(
                                    StringUtils.substringBefore(article.getTitle(), " -"),
                                    article.getDescription(),
                                    date.format(fmt) + " (por: " + article.getSource().getName() + ")",
                                    article.getUrl(),
                                    article.getUrlToImage()
                            ));
                        }

                        // Set adapter
                        view.setAdapter(new NewsAdapter(newsList, view));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        // Set error
                        newsList.add(new News(
                                "Erro ao resgatar notícias",
                                "Infelizmente não conseguimos resgatar nenhuma notícia do nosso servidor.\n" +
                                        "Favor contatar os publicadores do aplicativo e informar o erro.\n\n" +
                                        "Erro: " + throwable.getMessage(),
                                "Time COVIDApp",
                                "https://github.com/CerqueiraMatheus/SSC0103-COVIDApp",
                                "https://www.pinclipart.com/picdir/middle/576-5766852_frowning-face-emoji-clipart-smiley-png-download.png"
                        ));

                        // Set adapter
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
