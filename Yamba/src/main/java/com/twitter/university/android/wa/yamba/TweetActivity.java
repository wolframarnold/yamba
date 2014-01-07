package com.twitter.university.android.wa.yamba;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class TweetActivity extends Activity implements View.OnClickListener, TextWatcher {

    private static final String TAG = "TweetActivity";
    EditText mEditText;
    TextView mCharCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        Button buttonTweet = (Button) findViewById(R.id.button_tweet);
        buttonTweet.setOnClickListener(this);

        mEditText = (EditText) findViewById(R.id.edit_tweet);
        mEditText.addTextChangedListener(this);

        mCharCounter = (TextView) findViewById(R.id.text_char_count);
        mCharCounter.setText("140 " + getString(R.string.chars_remaining));
    }

    @Override
    public void afterTextChanged(Editable edt)
    {
        int remainingChars = 140-edt.length();
        mCharCounter.setText(String.valueOf(remainingChars) + " " + getString(R.string.chars_remaining));
        if (remainingChars <= 0) {
            mCharCounter.setTextColor(Color.RED);
        } else if (remainingChars <= 10) {
            mCharCounter.setTextColor(Color.BLUE);
        } else {
            mCharCounter.setTextColor(Color.DKGRAY);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) Log.v(TAG, "onStart() invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (BuildConfig.DEBUG) Log.v(TAG, "onRestart() invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) Log.v(TAG, "onResume() invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) Log.v(TAG, "onPause() invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) Log.v(TAG, "onStop() invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) Log.v(TAG, "onDestroy() invoked");
    }
// TODO -- make success/failure message a string resource

    @Override
    public void onClick(View view) {

        int id=view.getId();
        switch (id) {
            case R.id.button_tweet:
                // Handle button click
                String msg = mEditText.getText().toString();

                if (!TextUtils.isEmpty(msg)) {
                    Intent intent = new Intent(this, TweetService.class);
                    intent.putExtra("MESSAGE", msg);
                    startService(intent);
                }
                mEditText.setText("");

                break;
            default:
                // We should never get here!
        }
    }
//
//    private class PostTweet extends AsyncTask<String, Void, Boolean> {
//        Boolean success;
//
//        protected Boolean doInBackground(String... s) {
//            Log.v(TAG, s.toString());
//            try {
//                mYambaClient.postStatus(s[0]);
//                success = true;
//                Log.v(TAG, "Upload succeeded");
//            } catch (YambaClientException e) {
//                success = false;
//                Log.w(TAG, "Upload failed", e);
//            }
//            return success;
//        }
//
//        protected void onPostExecute(Boolean success) {
//            if (success) {
//                mToast.setText("Tweet posted!");
//                mToast.show();
//            } else {
//                mToast.setText("OOPS... something went wrong. Try again. Sorry we lost your Tweet.");
//                mToast.show();
//            }
//        }
//    }
}
