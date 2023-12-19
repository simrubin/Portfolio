package com.example.lab02.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Query("Select * from books")
    public LiveData<List<Book>> getAllBooks();

    @Query("Delete from books where book_id= :id")
    public void deleteBook(int id);

    @Insert
    public void addBookToLib(Book book);

    @Query("Delete from books")
    public void deleteAll();
}
