package org.acme.domain.models;

import java.util.List;
import java.util.UUID;

public class Author {

    private UUID uuid;
    private String name;
    private List<Genre> genre;
    private List<String> description;
    private String photo;

    public Author(){}

    public Author(UUID uuid, String name, List<Genre> genre, List<String> description, String photo) {
        this.uuid = uuid;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.photo = photo;
    }


    public static Author init(UUID uuid, String name, List<Genre> genre, List<String> description, String photo){
        return new Author(uuid, name, genre, description, photo);
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

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

