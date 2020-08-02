package com.moringaschool.hellozuz;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsDataTest {

    @Test
    public void getNews() {
       NewsData newsData = setNewsData();
       assertEquals("Hey come over here", newsData.getNews());
    }

    @Test
    public void setNews() {
        NewsData newsData = setNewsData();
        newsData.setNews("Hey stoop sleeping");
        assertEquals("Hey stoop sleeping", newsData.getNews());
    }

    @Test
    public void getName() {
        NewsData newsData = setNewsData();
        assertEquals("Monsoon", newsData.getName());
    }

    @Test
    public void setName() {
        NewsData newsData = setNewsData();
        newsData.setName("kevin");
        assertEquals("kevin", newsData.getName());
    }

    //helpers
    public NewsData setNewsData() {
        return new NewsData("Hey come over here", "Monsoon");
    }
}