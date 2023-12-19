package com.example.lab02;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab02.provider.Book;
import com.example.lab02.provider.BookViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {


    public static final String ID_KEY = "id_key";
    public static final String TITLE_KEY = "title_key";
    public static final String ISBN_KEY = "isbn_key";
    public static final String AUTHOR_KEY = "author_key";
    public static final String DESC_KEY = "desc_key";
    public static final String PRICE_KEY = "price_key";


    private EditText bookIdEt;
    private EditText titleEt;
    private EditText isbnEt;
    private EditText authorEt;
    private EditText descEt;
    private EditText priceEt;
    private EditText yearEt;
//    private EditText nameEt;


    private String id;
    private String title;
    private int isbn;
    private String author;
    private String description;
    private float price;
    private int year;


    //    ArrayList<String> dataSource;
    ArrayList<Item> data;

//    ListView listView;

//    ArrayAdapter<String> adapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;

    private BookViewModel bookViewModel;

    DatabaseReference database;
    DatabaseReference booksRef;

    View gestureView;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        data = new ArrayList<>();
//        dataSource = new ArrayList();
//        listView=findViewById(R.id.list_view);
        //recyclerView = findViewById(R.id.my_recycler_view);
        //layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        //recyclerView.setLayoutManager(layoutManager);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSource);
//        listView.setAdapter(adapter);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        adapter = new MyRecyclerAdapter();
        //adapter.setData(data);
        //recyclerView.setAdapter(adapter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new MyNavHandler());


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToRecyclerView();
//                title = titleEt.getText().toString();
//                price = Float.parseFloat((priceEt.getText().toString()));
//                dataSource.add(title +" | "+ price);
//                adapter.notifyDataSetChanged();
            }
        });

//        FloatingActionButton fab2 = findViewById(R.id.fab_2);
//        fab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Bachelor of IT and Design", Toast.LENGTH_SHORT).show();
//            }
//        });

        bookIdEt = findViewById(R.id.book_id);
        titleEt = findViewById(R.id.book_title_id);
        isbnEt = findViewById(R.id.book_isbn_id);
        authorEt = findViewById(R.id.book_author_id);
        descEt = findViewById(R.id.book_desc_id);
        priceEt = findViewById(R.id.book_price_id);
        yearEt = findViewById(R.id.book_year_et);
        year = 2023;
        yearEt.setText(String.valueOf(year));


//        nameEt = findViewById(R.id.name_id);


        if (savedInstanceState == null || savedInstanceState.isEmpty()) {
            loadsP();

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);

            MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();

            registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));
            registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));
        }

        //UI CONTROLLER FOR DB
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout_id, new ListBooksFragment())
                .addToBackStack("fdb").commit();

        gestureView = findViewById(R.id.gesture_view);
        gestureDetector = new GestureDetector(this, new MyGestureListener());

//        gestureView.setOnTouchListener(new View.OnTouchListener() {
//            int x1;
//            int x2;
//
//            int moveX;
//            int moveY;
//            int y1;
//            int y2;
//
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getActionMasked();
//
//                if (action == MotionEvent.ACTION_DOWN) {
//                    x1 = (int)event.getX();
//                    y1 = (int)event.getY();
//                } else if (action == MotionEvent.ACTION_MOVE) {
//                    moveX = (int)event.getX();
//                    moveY = (int)event.getY();
//                    if (Math.abs(y1 - moveY)<= 25) {
//                        if (x1 < moveX){
//                            priceEt.setText(String.valueOf(price+=1));
//                        }
//                    }
//                } else if (action == MotionEvent.ACTION_UP) {
//                    x2 = (int)event.getX();
//                    y2 = (int)event.getY();
//                    int disX = x2 - x1;
//                    int disY = y2 - y1;
//                    if (disX < 0 && Math.abs(disY) < 150) {
//                        addToRecyclerView();
//                    }
//                    else if (disY < 0 && Math.abs(disX) < 150){
//                        clearItems();
//                    }
//                    else if (disY > 0 && Math.abs(disX) < 150){
//                        Toast.makeText(MainActivity.this, "Simeon Rubin", Toast.LENGTH_SHORT).show();;
//                    }
//                }
//                return true;
//            }
//        });

        gestureView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    private boolean toggle = false; // for fragments


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sP = getSharedPreferences("bookApp.ext",MODE_PRIVATE);
        id = sP.getString(ID_KEY,"abc123");
        title = sP.getString(TITLE_KEY,"title");
        isbn = sP.getInt(ISBN_KEY,12345);
        author = sP.getString(AUTHOR_KEY,"author");
        description = sP.getString(DESC_KEY,"description");
        price = sP.getFloat(PRICE_KEY,25.0f);
    }

    public void onAddBookBtnClick () {

        String id = bookIdEt.getText().toString();
        String title = titleEt.getText().toString();
        int isbn = Integer.parseInt((isbnEt.getText().toString()));
        String author = authorEt.getText().toString();
        String description = descEt.getText().toString();
        float price = Float.parseFloat((priceEt.getText().toString()));

        String msg = "book (" + title + ") and the price (" + String.format("%.2f",price) + ")";

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getSharedPreferences("bookApp.ext", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ID_KEY, id);
        editor.putString(TITLE_KEY,title);
        editor.putInt(ISBN_KEY,isbn);
        editor.putString(AUTHOR_KEY,author);
        editor.putString(DESC_KEY,description);
        editor.putFloat(PRICE_KEY,price);
        editor.apply(); // be wary of this commit, could crash app
        ;
    }

//    public void addToListView() {
//        String id = bookIdEt.getText().toString();
//        String title = titleEt.getText().toString();
//        int isbn = Integer.parseInt((isbnEt.getText().toString()));
//        String author = authorEt.getText().toString();
//        String description = descEt.getText().toString();
//        float price = Float.parseFloat((priceEt.getText().toString()));
//
//        String msg = "book (" + title + ") and the price (" + String.format("%.2f",price) + ")";
//
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//
//        SharedPreferences sharedPreferences = getSharedPreferences("bookApp.ext", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(ID_KEY, id);
//        editor.putString(TITLE_KEY,title);
//        editor.putInt(ISBN_KEY,isbn);
//        editor.putString(AUTHOR_KEY,author);
//        editor.putString(DESC_KEY,description);
//        editor.putFloat(PRICE_KEY,price);
//        editor.apply(); // be wary of this commit, could crash app
//
//        dataSource.add(title +" | " + price);
//    }

    private void addToRecyclerView() {
        id = bookIdEt.getText().toString();
        title = titleEt.getText().toString();
        isbn = Integer.parseInt(isbnEt.getText().toString());
        author = authorEt.getText().toString();
        description = descEt.getText().toString();
        price = Float.parseFloat(priceEt.getText().toString());

        SharedPreferences sharedPreferences = getSharedPreferences("bookApp.ext", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ID_KEY, id);
        editor.putString(TITLE_KEY,title);
        editor.putInt(ISBN_KEY,isbn);
        editor.putString(AUTHOR_KEY,author);
        editor.putString(DESC_KEY,description);
        editor.putFloat(PRICE_KEY,price);
        editor.apply();

        Item item = new Item(id, title, isbn, author, description, price);
        data.add(item);
        adapter.notifyDataSetChanged();

        database = FirebaseDatabase.getInstance().getReference();
        booksRef = database.child("books");
        booksRef.push().setValue(item);

        Book book = new Book(title,isbn,author,description,price);
        bookViewModel.insertBookVM(book);
    }


    public void onClickClear(View view) {
        bookIdEt.setText(" ");
        titleEt.setText(" ");
        isbnEt.setText(" ");
        authorEt.setText(" ");
        descEt.setText(" ");
        priceEt.setText(" ");
    }

    public void clearItems(){
        bookIdEt.setText(" ");
        titleEt.setText(" ");
        isbnEt.setText(" ");
        authorEt.setText(" ");
        descEt.setText(" ");
        priceEt.setText(" ");
    }

    public void onClickUpperCase(View view) {
        String title = titleEt.getText().toString();


        if (title.equals(title.toUpperCase())) {
            titleEt.setText(title.toLowerCase());
        }
        else {

            titleEt.setText(title.toUpperCase());
        }
    }

    private void loadsP(){
        SharedPreferences sP = getSharedPreferences("bookApp.ext",MODE_PRIVATE);
        id = sP.getString(ID_KEY,"abc123");
        title = sP.getString(TITLE_KEY,"title");
        isbn = sP.getInt(ISBN_KEY,12345);
        author = sP.getString(AUTHOR_KEY,"author");
        description = sP.getString(DESC_KEY,"description");
        price = sP.getFloat(PRICE_KEY,25.0f);

        SharedPreferences.Editor editor = sP.edit();
        editor.putString(ID_KEY, id);
        editor.putString(TITLE_KEY,title);
        editor.putInt(ISBN_KEY,isbn);
        editor.putString(AUTHOR_KEY,author);
        editor.putString(DESC_KEY,description);
        editor.putFloat(PRICE_KEY,price);
        editor.apply();

        bookIdEt.setText(id);
        titleEt.setText(title);
        isbnEt.setText(String.valueOf(isbn));
        authorEt.setText(author);
        descEt.setText(description);
        priceEt.setText(String.valueOf(price));

    }

    public void onClickLoadBook(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("bookApp.ext", MODE_PRIVATE);
        id = sharedPreferences.getString(ID_KEY,"abc123");
        title = sharedPreferences.getString(TITLE_KEY,"title");
        isbn = sharedPreferences.getInt(ISBN_KEY,12345);
        author = sharedPreferences.getString(AUTHOR_KEY,"author");
        description = sharedPreferences.getString(DESC_KEY,"description");
        price = sharedPreferences.getFloat(PRICE_KEY,25.0f);

        bookIdEt.setText(id);
        titleEt.setText(title);
        isbnEt.setText(String.valueOf(isbn));
        authorEt.setText(author);
        descEt.setText(description);
        priceEt.setText(String.valueOf(price));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String title = titleEt.getText().toString();
        int isbn = Integer.parseInt((isbnEt.getText().toString()));

        outState.putString(TITLE_KEY, title);
        outState.putInt(ISBN_KEY, isbn);


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle inState) {

        title = inState.getString(TITLE_KEY);
        isbn = inState.getInt(ISBN_KEY);

        titleEt.setText(title); // Forgot to call the .setText function. Used ChatGPT to debug which helped remind me to call this function.
        isbnEt.setText(String.valueOf(isbn));

    }

    public void onSendBroadcast(){
        Intent intent= new Intent();
        intent.setAction("");
        intent.putExtra("key-value",15);
        sendBroadcast(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id==R.id.options_menu_clear_all){
            clearItems();
        } else if (id==R.id.options_menu_load_book) {
            loadsP();
        } else if (id==R.id.show_my_id) {
            Toast.makeText(this, "30568773", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

//    public void addBookToFirebase(){
//        String id = bookIdEt.getText().toString();
//        String title = titleEt.getText().toString();
//        int isbn = Integer.parseInt((isbnEt.getText().toString()));
//        String author = authorEt.getText().toString();
//        String description = descEt.getText().toString();
//        float price = Float.parseFloat((priceEt.getText().toString()));
//        Book book = new Book(id,title,isbn,author,description,price);
//
//    }

    public void sendStudIdFirebase(View view){

        String studId = "30568773";

        database = FirebaseDatabase.getInstance().getReference();
        booksRef = database.child("books");
        booksRef.push().setValue(studId);
    }

    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);

            StringTokenizer sT = new StringTokenizer(msg, ",");
            String id = sT.nextToken();
            String title = sT.nextToken();
            String isbn = sT.nextToken();
            String author = sT.nextToken();
            String description = sT.nextToken();
            String price = sT.nextToken();
//            String studid = sT.nextToken();
//            String name = sT.nextToken();

            bookIdEt.setText(id);
            titleEt.setText(title);
            isbnEt.setText(String.valueOf(isbn));
            authorEt.setText(author);
            descEt.setText(description);
            priceEt.setText(String.valueOf(price));
//            studidEt.setText(String.valueOf(studid));
//            nameEt.setText(name);
        }
    }

    class MyNavHandler implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            title = titleEt.getText().toString();
            price = Float.parseFloat((priceEt.getText().toString()));
            int id = item.getItemId();

                if (id==R.id.nav_menu_add_book){
                    addToRecyclerView();
                }
                else if (id==R.id.nav_menu_remove_last_book) {
                    int lastPosition = data.size() - 1;
                    if (lastPosition >= 0) {
                        data.remove(lastPosition);
                        adapter.notifyItemRemoved(lastPosition);
                    }                }
                else if (id==R.id.nav_menu_remove_all_books) {
//                    data.clear();
                    database = FirebaseDatabase.getInstance().getReference();
                    booksRef = database.child("books");
                    booksRef.removeValue();
                }
                else if (id==R.id.nav_menu_list_all) {
                    Intent intent = new Intent(MainActivity.this, ListBooksActivity.class);
                    startActivity(intent);
                }
            drawerLayout.closeDrawers();
            //adapter.notifyDataSetChanged();
            return true;
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            RandomString randomString = new RandomString();
            Random rand = new Random();
            int randomNum = rand.nextInt((8 - 2) + 1) + 2;
            String newISBN = randomString.generateNewRandomString(randomNum).toString();
            isbnEt.setText(newISBN);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            clearItems();
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceY) <= 800) {
                if (distanceX > 0) {
                    priceEt.setText(String.valueOf(price+=Math.abs((int)distanceX)));
                } else if (distanceX < 0) {
                    priceEt.setText(String.valueOf(price-=Math.abs((int)distanceX)));
                }
            }
            if (Math.abs(distanceX) <= 800){
                int yearCount = (2023);
                int yearVal = Integer.parseInt(String.valueOf(yearEt.getText()));
                if (distanceY > 0) {
                    yearEt.setText(String.valueOf(year+=Math.abs((int)distanceY)));
                } else if (distanceY < 0) {
                    yearEt.setText(String.valueOf(year-=Math.abs(((int)distanceY))));
                }

            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(velocityX) > 1000 || Math.abs(velocityY) > 1000){
                moveTaskToBack(true);
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            loadsP();
        }
    }
    public class RandomString {

        public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public final String lower = upper.toLowerCase();

        public static final String digits = "0123456789";

        public final String alphaNumeric = upper + lower + digits;


        public String generateNewRandomString(int length) {
            char[] buf;
            Random random=new Random();
            if (length < 1) throw new IllegalArgumentException();
            buf = new char[length];
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = alphaNumeric.charAt(random.nextInt(alphaNumeric.length()));
            return new String(buf);
        }
    }


}