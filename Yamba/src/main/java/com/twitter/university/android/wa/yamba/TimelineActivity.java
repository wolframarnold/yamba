package com.twitter.university.android.wa.yamba;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.twitter.university.android.wa.yamba.service.YambaContract;

public class TimelineActivity extends Activity {
    private static final String[] FROM = {
            YambaContract.Timeline.Columns.HANDLE,
            YambaContract.Timeline.Columns.TWEET,
    };
    private static final int[] TO = {
            android.R.id.text1,
            android.R.id.text2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Cursor cursor = getContentResolver().query(
                YambaContract.Timeline.URI,
                null,
                null,
                null,
                YambaContract.Timeline.Columns.TIMESTAMP + " DESC"
        );

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_expandable_list_item_2,
                cursor,
                FROM,
                TO,
                0
        );

        ListView listTimeline = (ListView) findViewById(R.id.list_timeline);
        listTimeline.setAdapter(adapter);
    }
}
