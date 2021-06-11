package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView viewCover;
    private String title;
    private String author;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = "swan+song"; // Test title
        author = "robert"; // Test author
        viewCover = findViewById(R.id.imageView);
    }

    public void onClickForTest(View view) throws InterruptedException {
        // Create a new searchManager and pass it the title and author
        SearchManager searchManager = new SearchManager(title, author);

        // Start a new thread to get the api results and deserialize them
        Thread searchBookThread = new Thread(searchManager);
        searchBookThread.start();
        searchBookThread.join();

        // This mess gets the ISBN from the first book returned from the search
        String isbn = searchManager.getBooks().getDocs().get(0).getIsbn().get(0);

        // This gets the title from the first book returned from the search
        String title = searchManager.getBooks().getDocs().get(0).getTitle();

        // This gets the first author from the author list from the first book returned from the search
        String author = searchManager.getBooks().getDocs().get(0).getAuthor().get(0);

        // Cover isn't saving to the directory, checking into better ways of saving the image
        CoverManager coverManager = new CoverManager(isbn);
        Thread searchCoverThread = new Thread(coverManager);
        searchCoverThread.start();
        searchCoverThread.join();

        // This should return the path to our cover image downloaded from the searchCoverThread
        String cover = coverManager.getCover(isbn);

        // This is the BookData where we will store the information about our book
        BookData book = new BookData(isbn, title, author, cover);

        // Logs the books data
        Log.d(TAG, book.toString());
    }
}