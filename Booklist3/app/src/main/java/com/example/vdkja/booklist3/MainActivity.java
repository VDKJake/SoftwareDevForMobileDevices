package com.example.vdkja.booklist3;

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

        addBookData();
    }

    private void addBookData()
    {
        Book book1 = new Book("A great book", "Rating: 5/10", R.drawable.test_icon);
        bookList.add(book1);

        Book book2 = new Book("Pretty good book", "Rating: 3/10", R.drawable.cat_icon);
        bookList.add(book2);

        Book book3 = new Book("Book Book", "Rating: 1/10", R.drawable.test_icon);
        bookList.add(book3);

        Book book4 = new Book("Koob", "Rating: 10/10", R.drawable.cat_icon);
        bookList.add(book4);

        Book book5 = new Book("Obok", "Rating: 6/10", R.drawable.test_icon);
        bookList.add(book5);

        Book book6 = new Book("Insert Book Title Here", "Rating: 2/10", R.drawable.cat_icon);
        bookList.add(book6);

        Book book7 = new Book("The Bookiest Book", "Rating: 2/10", R.drawable.test_icon);
        bookList.add(book7);

        Book book8 = new Book("Another great book", "Rating: 1/10", R.drawable.cat_icon);
        bookList.add(book8);

        Book book9 = new Book("Asoblutely Awful", "Rating: 7/10", R.drawable.test_icon);
        bookList.add(book9);

        Book book10 = new Book("Book", "Rating: 9/10", R.drawable.cat_icon);
        bookList.add(book10);

        bAdapter.notifyDataSetChanged();
    }
}
