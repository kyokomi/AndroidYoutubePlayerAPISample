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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

/**
 * A simple YouTube Android API demo application which shows how to create a simple application that
 * shows a YouTube Video in a {@link YouTubePlayerFragment}.
 * <p>
 * Note, this sample app extends from {@link YouTubeFailureRecoveryActivity} to handle errors, which
 * itself extends {@link YouTubeBaseActivity}. However, you are not required to extend
 * {@link YouTubeBaseActivity} if using {@link YouTubePlayerFragment}s.
 */
public class FragmentDemoActivity extends YouTubeFailureRecoveryActivity {

    boolean isBackground = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("#####", "onCreate");

        setContentView(R.layout.fragments_demo);

        findViewById(R.id.youtube_fragment_textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBackground = false;
                startActivity(new Intent(FragmentDemoActivity.this, PlayerViewDemoActivity.class));
            }
        });

        YouTubePlayerFragment youTubePlayerFragment =
            (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(DeveloperKey.DEVELOPER_KEY, this);
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
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("hoge", isBackground);
        super.onSaveInstanceState(bundle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isBackground = savedInstanceState.getBoolean("hoge");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored || !isBackground) {
            player.cueVideo("nCgQDjiotG0");
            isBackground = true;
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
    }

}
