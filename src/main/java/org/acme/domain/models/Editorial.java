package org.acme.domain.models;

import java.util.List;
import java.util.UUID;

public class Editorial {
    private UUID uuid;
    private String name;
    private List<Author> authors;
    private List<Book> books;
    private Know know;
    private String photo;


    public Editorial(){}

    public Editorial(UUID uuid, String name, List<Author> authors, List<Book> books, Know know, String photo) {
        this.uuid = uuid;
        this.name = name;
        this.authors = authors;
        this.books = books;
        this.know = know;
        this.photo = photo;
    }

    public static Editorial init(UUID uuid, String name, List<Author> authors, List<Book> books, Know know, String photo){
        return new Editorial(uuid, name, authors, books, know, photo);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Know getKnow() {
        return know;
    }

    public void setKnow(Know know) {
        this.know = know;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
