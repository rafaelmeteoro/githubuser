package br.com.rafael.githubuser.followers.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.application.GithubUserApplication;
import br.com.rafael.githubuser.core.view.BaseActivity;
import br.com.rafael.githubuser.followers.di.DaggerFollowersComponent;
import br.com.rafael.githubuser.followers.di.FollowersModule;
import butterknife.ButterKnife;

public class FollowersActivity extends BaseActivity implements FollowersContract.View {

    private static final String KEY_USERNAME = "key_username";

    @Inject
    FollowersContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        bindViews();
        setUpToolbar();
        inject();
        initializeContents(savedInstanceState);
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    private void setUpToolbar() {

    }

    private void inject() {
        DaggerFollowersComponent
                .builder()
                .libraryComponent(GithubUserApplication.get(this).getComponent())
                .followersModule(new FollowersModule(this))
                .build()
                .inject(this);
    }

    private void initializeContents(@Nullable Bundle savedState) {
        boolean shouldInitializeFromState = savedState != null;

        if (shouldInitializeFromState) {

        } else {

        }
    }

    public static class IntentBuilder {

        private Intent intent;

        private IntentBuilder(Context context) {
            intent = new Intent(context, FollowersActivity.class);
        }

        public static IntentBuilder builder(@NonNull Context context) {
            return new IntentBuilder(context);
        }

        public Intent create() {
            return intent;
        }

        public IntentBuilder username(@NonNull String username) {
            intent.putExtra(KEY_USERNAME, username);
            return this;
        }
    }
}
