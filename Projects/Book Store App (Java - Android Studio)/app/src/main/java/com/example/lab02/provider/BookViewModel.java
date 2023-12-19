package com.example.lab02.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private BookRepository bookRepository;
    LiveData<List<Book>> myBooks;
    public BookViewModel(@NonNull Application application) {
        super(application);

        bookRepository = new BookRepository(application);
        myBooks=bookRepository.getMyBooks();
    }

    public LiveData<List<Book>> getMyBooks() {
        return myBooks;
    }

    public void insertBookVM(Book book){
        bookRepository.addBookRepo(book);
    }

    public void deleteBookVM(Book book){
        bookRepository.deleteBookRepo(book);
    }
}
