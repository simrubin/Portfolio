package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        // load new fragment inside frame layout
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout_id,new ListBooksFragment())
                .addToBackStack("fdb").commit();
    }
}