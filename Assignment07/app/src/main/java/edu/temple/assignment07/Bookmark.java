package edu.temple.assignment07;

import android.os.Parcel;
import android.os.Parcelable;

public class Bookmark implements Parcelable {
    String url;
    String title;

    public Bookmark(String url, String title) {
        this.url = url;
        this.title = title;
    }

    protected Bookmark(Parcel in) {
        url = in.readString();
        title = in.readString();
    }

    public static final Creator<Bookmark> CREATOR = new Creator<Bookmark>() {
        @Override
        public Bookmark createFromParcel(Parcel in) {
            return new Bookmark(in);
        }

        @Override
        public Bookmark[] newArray(int size) {
            return new Bookmark[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(title);
    }
}

