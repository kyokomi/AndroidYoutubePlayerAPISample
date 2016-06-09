/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.examples.youtubeapidemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple YouTube Android API demo application which shows how to create a simple application that
 * shows a YouTube Video in a {@link YouTubePlayerFragment}.
 * <p>
 * Note, this sample app extends from {@link YouTubeFailureRecoveryActivity} to handle errors, which
 * itself extends {@link YouTubeBaseActivity}. However, you are not required to extend
 * {@link YouTubeBaseActivity} if using {@link YouTubePlayerFragment}s.
 */
public class FragmentDemoActivity extends YouTubeFailureRecoveryActivity {

    static Intent getCallingIntent(Context context,
                                   ArrayList<String> stackList) {
        final Intent intent = new Intent(context, FragmentDemoActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.putStringArrayListExtra("stackList", stackList);
        return intent;
    }

    private List<String> stackList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("#####", "onCreate");

        setContentView(R.layout.fragments_demo);

        findViewById(R.id.youtube_fragment_textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stackList.add("nCgQDjiotG0"); // 次のやつ
                startActivity(getCallingIntent(FragmentDemoActivity.this,
                    (ArrayList<String>) stackList));
                finish();
            }
        });

        this.initializeArgs();

        if (savedInstanceState == null) {
            YouTubePlayerFragment youTubePlayerFragment = YouTubePlayerFragment.newInstance();
            getFragmentManager().beginTransaction()
                .add(R.id.youtube_fragment_container, youTubePlayerFragment)
                .commit();
            youTubePlayerFragment.initialize(DeveloperKey.DEVELOPER_KEY, this);
        }
    }

    private void initializeArgs() {
        if (getIntent() == null) {
            throw new IllegalArgumentException("no args video");
        }
        this.stackList = getIntent().getStringArrayListExtra("stackList");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("#####", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("#####", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("#####", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("#####", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("#####", "onDestroy");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(stackList.get(stackList.size() - 1));
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
    }

    @Override
    public void onBackPressed() {
        stackList.remove(stackList.size() - 1); // 今表示してるやつを除いて前の画面へ
        if (stackList.isEmpty()) {
            super.onBackPressed();
        } else {
            startActivity(getCallingIntent(FragmentDemoActivity.this,
                (ArrayList<String>) stackList));
            finish();
        }
    }
}
