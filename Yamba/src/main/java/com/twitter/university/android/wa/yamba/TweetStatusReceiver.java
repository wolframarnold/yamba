package com.twitter.university.android.wa.yamba;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TweetStatusReceiver extends BroadcastReceiver {

    public TweetStatusReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean result = intent.getBooleanExtra(TweetService.EXTRA_TWEET_STATUS, false);
        Toast.makeText(
                context,
                result ? "Tweet posted!" : "OOPS... something went wrong. Try again. Sorry we lost your Tweet.",
                Toast.LENGTH_LONG
        ).show();

    }
}
