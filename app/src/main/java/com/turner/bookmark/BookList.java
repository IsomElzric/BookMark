package com.turner.bookmark;

import java.util.ArrayList;
import java.util.List;

// class for storing a collection of books
public class BookList {

    private final List<BookDocument> fullList;
    private final List<BookDocument> currentList;
    private final List<BookDocument> completedList;


    public BookList() {
        fullList = new ArrayList<>();
        currentList = new ArrayList<>();
        completedList = new ArrayList<>();
    }
    // add a book to the fullList and currentList
    public void addCurrent(BookDocument book) {
        fullList.add(book);
        currentList.add(book);
    }
    // remove from currentList and add to completedList
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

    //call internalStorage.java
}
