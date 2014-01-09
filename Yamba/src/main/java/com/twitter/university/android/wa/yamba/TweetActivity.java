package com.twitter.university.android.wa.yamba;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class TweetActivity extends Activity implements TweetFragment.OnTweetCompletedListener {

    private static final String TAG = "TweetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        TweetFragment tweetFragment = (TweetFragment) getFragmentManager().findFragmentById(R.id.fragment_tweet);
        tweetFragment.setOnTweetCompletedListener(this);
    }

    @Override
    public void onTweetCompleted() {
        finish();
    }
}
