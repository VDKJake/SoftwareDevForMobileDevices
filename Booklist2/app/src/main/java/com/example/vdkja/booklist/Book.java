package com.example.vdkja.booklist;

import android.graphics.drawable.Drawable;

public class Book {
    public String bookTitle, bookRating;
    public int bookIcon;
    public Book()
    {

    }

    public Book(String title, String rating)
    {
        bookTitle = title;
        bookRating = rating;
        bookIcon = R.drawable.ic_launcher_foreground;
    }
}
