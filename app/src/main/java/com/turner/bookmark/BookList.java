package com.turner.bookmark;

import java.io.Serializable;
import java.util.List;

public class BookList implements Serializable {

    private List<BookDocument> fullList;
    private List<BookDocument> currentList;
    private List<BookDocument> completedList;

    public void addCurrent(BookDocument book) {
        fullList.add(book);
        currentList.add(book);
    }

    public void addCompleted(BookDocument book) {
        currentList.remove(book);
        completedList.add(book);
    }

    public List<BookDocument> getFullList() {
        return fullList;
    }

    public List<BookDocument> getCurrentList() {
        return currentList;
    }

    public List<BookDocument> getCompletedList() {
        return completedList;
    }
}
