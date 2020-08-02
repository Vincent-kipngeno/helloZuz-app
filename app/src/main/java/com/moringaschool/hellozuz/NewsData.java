package com.moringaschool.hellozuz;

public class NewsData {
    String news;
    String name;

    public NewsData(String news, String name) {
        this.news = news;
        this.name = name;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
