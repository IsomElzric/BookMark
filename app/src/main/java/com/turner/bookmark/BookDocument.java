package com.turner.bookmark;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BookDocument {

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
