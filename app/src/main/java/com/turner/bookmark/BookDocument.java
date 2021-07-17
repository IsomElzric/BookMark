package com.turner.bookmark;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
// class for storing book information
public class BookDocument implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("author_name")
    private List<String> author;
    @SerializedName("isbn")
    private List<String> isbn;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor() {
        return author;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public String toString() {
        return String.format("%s by %s", title, author.get(0));
    }
}
