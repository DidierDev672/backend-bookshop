package org.acme.domain.models;

import java.util.List;
import java.util.UUID;

public class Book {

    private UUID uuid;
    private String title;
    private List<Author> authors;
    private Editorial editorial;
    private DetailModel detailModel;
    private DescriptionModel descriptionModel;
    private String photo;

    public Book() {}

    public Book(
        UUID uuid,
        String title,
        List<Author> authors,
        Editorial editorial,
        DetailModel detailModel,
        DescriptionModel descriptionModel,
        String photo
    ) {
        this.uuid = uuid;
        this.title = title;
        this.authors = authors;
        this.editorial = editorial;
        this.detailModel = detailModel;
        this.descriptionModel = descriptionModel;
        this.photo = photo;
    }

    public static Book init(
        UUID uuid,
        String title,
        List<Author> authors,
        Editorial editorial,
        DetailModel detailModel,
        DescriptionModel descriptionModel,
        String photo
    ) {
        return new Book(
            uuid,
            title,
            authors,
            editorial,
            detailModel,
            descriptionModel,
            photo
        );
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public DetailModel getDetailModel() {
        return detailModel;
    }

    public void setDetailModel(DetailModel detailModel) {
        this.detailModel = detailModel;
    }

    public DescriptionModel getDescriptionModel() {
        return descriptionModel;
    }

    public void setDescriptionModel(DescriptionModel descriptionModel) {
        this.descriptionModel = descriptionModel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
