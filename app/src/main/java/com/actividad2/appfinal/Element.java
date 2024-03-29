package com.actividad2.appfinal;

public class Element {
    private long id;
    private long userId;
    private String title;
    private String description;
    private int imageResource;

    public Element(long id, long userId, String title, String description, int imageResource) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
