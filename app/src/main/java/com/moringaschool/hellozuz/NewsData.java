package com.moringaschool.hellozuz;

public class NewsData {
    String news;
    String name;
    int imgResId;

    public NewsData(String news, String name, int imgResId) {
        this.news = news;
        this.name = name;
        this.imgResId = imgResId;
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

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
