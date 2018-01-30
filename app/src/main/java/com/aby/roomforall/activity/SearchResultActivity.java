package com.aby.roomforall.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.aby.roomforall.R;
import com.aby.roomforall.adapter.SearchResultAdapter;
import com.aby.roomforall.dto.Trip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(0);
        }

        toolbar.setTitle("");

        Typeface caviarDreams = Typeface.createFromAsset(this.getAssets(), "fonts/caviar_dreams_bold.ttf");
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setTypeface(caviarDreams);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        prepareList();
    }

    private void prepareList() {
        Intent intent = getIntent();
        if (intent.hasExtra("resultList")) {
            Trip[] items = (Trip[]) intent.getSerializableExtra("resultList");
            ListView listView = (ListView) findViewById(R.id.results_list);
            LinearLayout noResultsView = (LinearLayout) findViewById(R.id.no_results);
            LinearLayout resultsView = (LinearLayout) findViewById(R.id.results_view);
            if (items.length <= 0) {
                resultsView.setVisibility(View.GONE);
                noResultsView.setVisibility(View.VISIBLE);
            } else {
                noResultsView.setVisibility(View.GONE);
                resultsView.setVisibility(View.VISIBLE);

                List<Trip> results = new ArrayList<>();
                results.addAll(Arrays.asList(items));
                SearchResultAdapter adapter = new SearchResultAdapter(this, results);
                
                listView.setAdapter(adapter);
            }

        }
    }
}
