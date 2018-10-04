package com.example.vdkja.booklist3;

import android.graphics.drawable.Drawable;

public class Book {
    public String bookTitle, bookRating;
    public int bookIcon;
    public Book()
    {

    }

    public Book(String title, String rating, int icon)
    {
        bookTitle = title;
        bookRating = rating;
        bookIcon = icon;
    }
}
