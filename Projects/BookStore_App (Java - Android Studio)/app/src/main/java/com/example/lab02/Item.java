package com.example.lab02;

public class Item {

    private String id;
    private String title;
    private int isbn;
    private String author;
    private String description;
    private float price;


    public Item(String id, String title, int isbn, String author, String description, float price) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }
}