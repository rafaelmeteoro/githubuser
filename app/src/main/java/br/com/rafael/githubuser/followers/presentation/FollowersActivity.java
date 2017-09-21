package br.com.rafael.githubuser.followers.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kennyc.view.MultiStateView;

import javax.inject.Inject;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.application.GithubUserApplication;
import br.com.rafael.githubuser.core.view.BaseActivity;
import br.com.rafael.githubuser.followers.di.DaggerFollowersComponent;
import br.com.rafael.githubuser.followers.di.FollowersModule;
import br.com.rafael.githubuser.followers.presentation.adapter.FollowersAdapter;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

public class FollowersActivity extends BaseActivity implements FollowersContract.View {

    private static final String KEY_USERNAME = "key_username";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.state_view)
    MultiStateView stateView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    FollowersContract.Presenter presenter;

    @Inject
    FollowersAdapter adapter;

    @State
    FollowersViewModelHolder holder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        bindViews();
        setUpToolbar();
        inject();
        initializeViews();
        initializeContents(savedInstanceState);
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.followers_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inject() {
        DaggerFollowersComponent
                .builder()
                .libraryComponent(GithubUserApplication.get(this).getComponent())
                .followersModule(new FollowersModule(this))
                .build()
                .inject(this);
    }

    private void initializeViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        stateView.setAnimateLayoutChanges(true);
    }

    private void initializeContents(@Nullable Bundle savedState) {
        boolean shouldInitializeFromState = savedState != null;

        if (shouldInitializeFromState) {
            presenter.initializeFromState(holder);
        } else {
            String username = getIntent().getStringExtra(KEY_USERNAME);
            presenter.initialize(username);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showFollowers(FollowersViewModelHolder holder) {
        this.holder = holder;

        stateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        adapter.setData(holder);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFollowersLoading() {
        stateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void showFollowersError() {
        stateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
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
