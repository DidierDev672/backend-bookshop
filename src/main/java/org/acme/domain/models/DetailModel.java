package org.acme.domain.models;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DetailModel {
    private String title;
    private List<Author> authors;
    private List<Genre> genres;
    private String ISBN;
    private String language;
    private int pages;
    private int year;

    public DetailModel(){}

    public DetailModel(String title, List<Author> authors, List<Genre> genres, String ISBN, String language, int pages, int year){
        this.title = title;
        this.authors = authors;
        this.genres = genres;
        this.ISBN = ISBN;
        this.language = language;
        this.pages = pages;
        this.year = year;
    }

    public static DetailModel init(String title, List<Author> authors, List<Genre> genres, String ISBN, String language, int pages, int year){
        return new DetailModel(title, authors, genres, ISBN, language, pages, year);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getISBN(){
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
