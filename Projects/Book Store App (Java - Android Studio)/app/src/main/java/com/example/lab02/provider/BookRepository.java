package com.example.lab02.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRepository {

    private BookDao bookDao;
    private LiveData<List<Book>> myBooks;

    public BookRepository(Application app) {
        LibDatabase db = LibDatabase.getDatabase(app);

        bookDao=db.bookDao();
        myBooks=bookDao.getAllBooks();
    }

    public LiveData<List<Book>> getMyBooks() {
        return myBooks;
    }

    public void addBookRepo(Book book){
        LibDatabase.databaseWriteExecutor.execute(()->{
            bookDao.addBookToLib(book);
        });
    }

    public void deleteBookRepo(Book book){
        LibDatabase.databaseWriteExecutor.execute(()->{
            bookDao.deleteAll();
        });
    }
}
