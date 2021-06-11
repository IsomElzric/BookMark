package com.turner.bookmark;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchDocuments {

    @SerializedName("docs")
    private List<BookDocument> docs;

    public List<BookDocument> getDocs() {
        return docs;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (BookDocument book : docs) {
            result.append(book.toString());
            result.append("\n");
        }
        return result.toString();
    }
}
