package com.twitter.university.android.wa.yamba;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
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
    YambaClient mYambaClient;

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

        mYambaClient = new YambaClient("student", "password");
    }

    @Override
    public void afterTextChanged(Editable edt)
    {
        mCharCounter.setText(String.valueOf((140-edt.length())) + " " + getString(R.string.chars_remaining));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    // TextWatcher
    // Spannable
    // Green while lots of chars left
    // Yellow while only a few left
    // Red when overshot

    @Override
    public void onClick(View view) {

        int id=view.getId();
        switch (id) {
            case R.id.button_tweet:
                // Handle button click
                String msg = mEditText.getText().toString();

                new PostTweet().execute(msg);

                mEditText.setText("");

                break;
            default:
                // We should never get here!
        }
    }

    private class PostTweet extends AsyncTask<String, Integer, Boolean> {
        Boolean success;

        protected Boolean doInBackground(String... s) {
            Log.v(TAG, s.toString());
            try {
                mYambaClient.postStatus(s[0]);
                success = true;
                Log.v(TAG, "Upload succeeded");
            } catch (YambaClientException e) {
                success = false;
                Log.w(TAG, "Upload failed", e);
            }
            return success;
        }

        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(TweetActivity.this, "Tweet posted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TweetActivity.this, "OOPS... something went wrong. Try again. Sorry we lost your Tweet.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
