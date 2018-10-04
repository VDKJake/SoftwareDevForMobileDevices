package com.example.vdkja.booklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Book> bookList = new ArrayList<>();
    private RecyclerView rView;
    private BookAdapter bAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rView = (RecyclerView) findViewById(R.id.recyclerView);
        bAdapter = new BookAdapter(bookList);
        RecyclerView.LayoutManager mLManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.setAdapter(bAdapter);

        //addBookData();
    }

    /*private void addBookData()
    {
        Book book1 = new Book("A great book", "Rating: 5/10", R.drawable.test_icon);
        bookList.add(book1);

        Book book2 = new Book("Pretty good book", "Rating: 3/10", R.drawable.cat_icon);
        bookList.add(book2);

        book = new Book("Book Book", "Rating: 1/10", R.drawable.test_icon);
        bookList.add(book);

        book = new Book("Koob", "Rating: 10/10", R.drawable.cat_icon);
        bookList.add(book);

        book = new Book("Obok", "Rating: 6/10", R.drawable.test_icon);
        bookList.add(book);

        book = new Book("Insert Book Title Here", "Rating: 2/10", R.drawable.cat_icon);
        bookList.add(book);

        book = new Book("The Bookiest Book", "Rating: 2/10", R.drawable.test_icon);
        bookList.add(book);

        book = new Book("Another great book", "Rating: 1/10", R.drawable.cat_icon);
        bookList.add(book);

        book = new Book("Asoblutely Awful", "Rating: 7/10", R.drawable.test_icon);
        bookList.add(book);

        book = new Book("Book", "Rating: 9/10", R.drawable.cat_icon);
        bookList.add(book);

        bAdapter.notifyDataSetChanged();
    }*/
}
