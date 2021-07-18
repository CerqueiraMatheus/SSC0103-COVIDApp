package com.poo.covidapp.Util.Models;

public class News {
    String title;
    String body;
    String published;
    String url;

    public News(String title, String body, String published, String url) {
        this.title = title;
        this.body = body;
        this.published = published;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getPublished() {
        return published;
    }

    public String getUrl() {
        return url;
    }
}