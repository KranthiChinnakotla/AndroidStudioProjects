package com.example.tejakanchinadam.inclass07app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import it.sephiroth.android.library.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {


    TextView tx1, tx2, tx3, tx4, tx5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Stories story = (Stories) getIntent().getExtras().getSerializable("story");

        tx1 = (TextView) findViewById(R.id.tx1);
        tx2 = (TextView) findViewById(R.id.tx2);
        tx4 = (TextView) findViewById(R.id.tx4);
        tx5 = (TextView) findViewById(R.id.tx5);


        tx1.setText(story.getStoryTitle());

        tx2.setText(story.getByLine());


        tx4.setText("Abstract");

        tx5.setText(story.getAbstractNY());


        ImageView img = (ImageView) findViewById(R.id.image1);

        Picasso.with(this).load(story.getImageURL()).into(img);



    }
}
