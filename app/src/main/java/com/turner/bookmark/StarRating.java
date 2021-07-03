package com.turner.bookmark;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StarRating extends AppCompatActivity {
    RatingBar ratingBar;
    String rateMessage;

    protected void starRating(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);

        ratingBar = findViewById(R.id.book_rating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
                rateMessage = String.valueOf(ratingBar.getRating());

            }
        });
    }
}
