package com.twitter.university.android.wa.yamba;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class TweetService extends IntentService {
    private static final String TAG = "TweetService";
    public static final String ACTION_TWEET_STATUS = "com.twitter.university.android.wa.ACTION_TWEET_STATUS";
    public static final String EXTRA_TWEET_STATUS = "com.twitter.university.android.wa.EXTRA_TWEET_STATUS";
    private YambaClient mYambaClient;

    public TweetService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mYambaClient = new YambaClient("student", "password");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String msg = intent.getStringExtra("MESSAGE");
        if (!TextUtils.isEmpty(msg)) {
            boolean success = false;
            try {
                mYambaClient.postStatus(msg);
                success = true;
                Log.d(TAG, "Successfully posted tweet: "+msg);
            } catch (YambaClientException e) {
                Log.e(TAG, "Upload failed", e);
            }
            Intent broadcastIntent = new Intent(ACTION_TWEET_STATUS);
            broadcastIntent.putExtra(EXTRA_TWEET_STATUS, success);
            sendBroadcast(broadcastIntent);
        }
    }
}
