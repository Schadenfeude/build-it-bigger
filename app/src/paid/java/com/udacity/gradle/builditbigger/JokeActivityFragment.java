package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itrided.android.jokedetail.JokeDetailActivity;
import com.udacity.gradle.builditbigger.databinding.FragmentJokeBinding;

public class JokeActivityFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private FragmentJokeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJokeBinding.inflate(inflater, container, false);
        binding.tellJoke.setOnClickListener(v -> {
            final JokeTellerAsyncTask task = new JokeTellerAsyncTask(getJokerCallback());
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            hideProgress();
        }
    }

    private JokerCallback getJokerCallback() {
        return new JokerCallback() {
            @Override
            public void takeABreath() {
                binding.instructionsTextView.setVisibility(View.GONE);
                binding.tellJoke.setVisibility(View.GONE);
                binding.progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void makeMeLaugh(@NonNull String joke) {
                showJokeDetail(joke);
            }
        };
    }

    private void showJokeDetail(String joke) {
        final Intent intent = new Intent(getContext(), JokeDetailActivity.class);
        intent.putExtra(JokeDetailActivity.JOKE_EXTRA_NAME, joke);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void hideProgress() {
        binding.instructionsTextView.setVisibility(View.VISIBLE);
        binding.tellJoke.setVisibility(View.VISIBLE);
        binding.progress.setVisibility(View.GONE);
    }
}
