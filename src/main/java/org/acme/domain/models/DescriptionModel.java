package org.acme.domain.models;

import java.util.List;

public class DescriptionModel {
    private String title;
    private List<String> description;

    public DescriptionModel(){}

    public DescriptionModel(String title, List<String> description){
        this.title = title;
        this.description = description;
    }

    public static DescriptionModel init(String title, List<String> description){
        return new DescriptionModel(title, description);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
