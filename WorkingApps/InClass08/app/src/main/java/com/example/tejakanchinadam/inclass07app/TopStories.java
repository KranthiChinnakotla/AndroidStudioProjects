package com.example.tejakanchinadam.inclass07app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class TopStories extends AppCompatActivity implements GetStories.News {

    ListView listView;
    static ProgressDialog pg;

    ImageView bookmarkImage;

    DatabaseDataManager dm;

    ArrayList<Stories> finSt;

    String section;
    Stories story;

    int count = 1;

    int lastID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);

        listView = (ListView) findViewById(R.id.listView);
        pg = new ProgressDialog(this);

        section = getIntent().getExtras().get(MainActivity.choice_key).toString();

        String utl = "http://api.nytimes.com/svc/topstories/v1/" + section + ".json?api-key=86f459c74288138406e0f0339c15f1c1:13:74582585";

        new GetStories(this).execute(utl);

        //lastID = R.drawable.bookmark_empty;

        //clearAllBookmark();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dm = new DatabaseDataManager(TopStories.this);
                //story = finSt.get(position);

                story = new Stories(finSt.get(position).getAbstractNY(), finSt.get(position).getByLine(),
                        section, finSt.get(position).getDate(), finSt.get(position).getImageURL(),
                        finSt.get(position).getStoryTitle(), finSt.get(position).getThumbnail());

                if (dm.getStory(story.getStoryTitle())!= null) {
                    dm.deleteStory(dm.getStory(story.getStoryTitle()));
                    ImageView ivFav = (ImageView) view.findViewById(R.id.bookmark_image);
                    ivFav.setImageResource(R.drawable.bookmark_empty);
                    story.setIsBookmarkChecked(false);
                    //appListDeletable.remove(list.get(position));
                    //adapter.setNotifyOnChange(true);
                    Toast.makeText(getApplicationContext(), "Bookmark Removed", Toast.LENGTH_SHORT).show();

                } else {
                    dm.saveStory(story);
                    ImageView ivFav = (ImageView) view.findViewById(R.id.bookmark_image);
                    ivFav.setImageResource(R.drawable.bookmark_filled);
                    story.setIsBookmarkChecked(true);
                    //appListDeletable.add(appList.get(position));
                    Toast.makeText(getApplicationContext(), "Bookmark Added", Toast.LENGTH_SHORT).show();
                }

                return true;

            }
        });


    }



    @Override
    public void newslist(ArrayList<Stories> st) {

        finSt = st;

        NYTopStoriesAdapter adapter = new NYTopStoriesAdapter(TopStories.this, R.layout.news_layout_stories, st);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(TopStories.this, DetailsActivity.class);
                intent.putExtra("story", finSt.get(position));
                //intent.putExtra("boolean", story.getIsBookmarkChecked());

                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bookmark, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {
            case R.id.showBookmark:
                showAllBookmark();
                return true;
            case R.id.clearBookmark:
                clearAllBookmark();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void showAllBookmark() {


        dm = new DatabaseDataManager(TopStories.this);
        ArrayList<Stories> newLst = (ArrayList<Stories>) dm.getAllStoriesByCategory(section);
        NYTopStoriesAdapter adapter = new NYTopStoriesAdapter(TopStories.this, R.layout.news_layout_stories, newLst);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void clearAllBookmark() {

        dm.deleteAll();

        NYTopStoriesAdapter adapter = new NYTopStoriesAdapter(TopStories.this, R.layout.news_layout_stories, finSt);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }


}
