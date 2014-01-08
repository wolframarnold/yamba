package com.twitter.university.android.wa.yamba;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TweetFragment extends Fragment implements View.OnClickListener, TextWatcher {

    EditText mEditText;
    TextView mCharCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View top = inflater.inflate(R.layout.fragment_tweet, container, false);

        Button buttonTweet = (Button) top.findViewById(R.id.button_tweet);
        buttonTweet.setOnClickListener(this);

        mEditText = (EditText) top.findViewById(R.id.edit_tweet);
        mEditText.addTextChangedListener(this);

        mCharCounter = (TextView) top.findViewById(R.id.text_char_count);
        mCharCounter.setText("140 " + getString(R.string.chars_remaining));

        return top;
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
    public void onClick(View view) {

        int id=view.getId();
        switch (id) {
            case R.id.button_tweet:
                // Handle button click
                String msg = mEditText.getText().toString();

                if (!TextUtils.isEmpty(msg)) {
                    Intent intent = new Intent(getActivity(), TweetService.class);
                    intent.putExtra("MESSAGE", msg);
                    getActivity().startService(intent);
                }
                mEditText.setText("");
                getActivity().finish();
                break;
            default:
                // We should never get here!
        }
    }

}
