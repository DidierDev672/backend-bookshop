package org.acme.domain.models;

import java.util.List;

public class Know {
    private String title;
    private List<String> description;

    public Know(){}

    public Know(String title, List<String> description){
        this.title = title;
        this.description = description;
    }

    public static Know init(String title, List<String> description){
        return new Know(title, description);
    }

    public String getTitle() {
        return title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
