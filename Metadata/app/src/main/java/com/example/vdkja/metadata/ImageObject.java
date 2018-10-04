package com.example.vdkja.metadata;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageObject implements Parcelable {

    private String name = "";
    private String URL = "";
    private String keywords = "";
    private String date = "";
    private String email = "";
    boolean share = false;
    private int imageID = 0;
    private int rating = 0;

    public ImageObject(String name, String URL, String keywords, String date, String email, boolean share, int imageID, int rating)
    {
        update(name, URL, keywords, date, email, share, imageID, rating);
    }

    private void update(String name, String URL, String keywords, String date, String email, boolean share, int imageID, int rating)
    {
        this.name = name;
        this.URL = URL;
        this.keywords = keywords;
        this.date = date;
        this.email = email;
        this.share = share;
        this.imageID = imageID;
        this.rating = rating;

    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(URL);
        dest.writeString(keywords);
        dest.writeString(date);
        dest.writeString(email);
        dest.writeInt(share ? 1 : 0);
        dest.writeInt(imageID);
        dest.writeInt(rating);
    }

    public static final Creator<ImageObject> CREATOR = new Creator<ImageObject>() {
        @Override
        public ImageObject createFromParcel(Parcel source)
        {
            return new ImageObject(source);
        }

        @Override
        public ImageObject[] newArray(int size)
        {
            return new ImageObject[size];
        }
    };

    private ImageObject(Parcel source)
    {

    }

    public String toString()
    {
        return name + "\nObtained On: " + date;
    }
}
