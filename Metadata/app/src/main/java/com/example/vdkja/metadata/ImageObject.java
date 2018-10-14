package com.example.vdkja.metadata;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.versionedparcelable.VersionedParcel;

public class ImageObject implements Parcelable {

    private String name = "";
    private String URL = "";
    private String keywords = "";
    private Date date;
    private String email = "";
    boolean share = false;
    private int imageID = 0;
    private float rating = 0.0f;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ImageObject(String name, String URL, String keywords, String date, String email, boolean share, int imageID, float rating)
    {
        Date dateTemp = Calendar.getInstance().getTime();
        try{
            dateTemp = dateFormat.parse(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        update(name, URL, keywords, dateTemp, email, share, imageID, rating);
    }

    private void update(String name, String URL, String keywords, Date date, String email, boolean share, int imageID, float rating)
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
        dest.writeString(dateFormat.format(date));
        dest.writeString(email);
        dest.writeInt(share ? 1 : 0);
        dest.writeInt(imageID);
        dest.writeFloat(rating);
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
        name = source.readString();
        URL = source.readString();
        keywords = source.readString();
        try{
            date = dateFormat.parse(source.readString());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        email = source.readString();
        share = source.readInt() == 1;
        imageID = source.readInt();
        rating = source.readFloat();
    }

    public String toString()
    {
        return name + "\nObtained On: " + dateFormat.format(date);
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    public String getKeywords() {
        return keywords;
    }

    public Date getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public boolean isShared() {
        return share;
    }

    public float getRating() {
        return rating;
    }

    public int getImageID()
    {
        return imageID;
    }
}
