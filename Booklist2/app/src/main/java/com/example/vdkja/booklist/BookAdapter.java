package com.example.vdkja.booklist;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.AViewHolder> {

    private List<Book> books;

    public class AViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, rating;
        public ImageView icon;
        public AViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            rating = (TextView) view.findViewById(R.id.rating);
            icon = (ImageView) view.findViewById(R.id.bookIcon);
        }
    }

    public BookAdapter(List<Book> iBooks)
    {
        books = iBooks;
    }

    @NonNull
    @Override
    public AViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View bookView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_list_row, viewGroup, false);
        return new AViewHolder(bookView);
    }

    @Override
    public void onBindViewHolder(@NonNull AViewHolder aViewHolder, int i) {
        Book book = books.get(i);
        aViewHolder.title.setText(book.bookTitle);
        aViewHolder.rating.setText(book.bookRating);
        aViewHolder.icon.setImageResource(book.bookIcon);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}