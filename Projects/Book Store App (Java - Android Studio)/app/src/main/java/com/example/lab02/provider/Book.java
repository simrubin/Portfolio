package com.example.lab02.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Book.TABLE_NAME)
public class Book {

    public static final String TABLE_NAME = "books";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "book_id")
    private int id;
    @ColumnInfo(name = "book_title")
    private String title;
    @ColumnInfo(name = "book_isbn")
    private int isbn;
    @ColumnInfo(name = "book_author")
    private String author;
    @ColumnInfo(name = "book_description")
    private String description;
    @ColumnInfo(name = "book_price")
    private float price;


    public Book( String title, int isbn, String author, String description, float price) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.description = description;
        this.price = price;

    }

    public void setId(@NonNull int id) {
        this.id = id;
    }


    @NonNull
    public int getId() {
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
