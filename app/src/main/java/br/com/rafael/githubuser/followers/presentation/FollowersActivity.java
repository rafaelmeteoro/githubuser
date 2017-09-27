package br.com.rafael.githubuser.followers.presentation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.kennyc.view.MultiStateView;

import javax.inject.Inject;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.application.GithubUserApplication;
import br.com.rafael.githubuser.core.di.HasComponent;
import br.com.rafael.githubuser.core.view.BaseActivity;
import br.com.rafael.githubuser.followers.di.DaggerFollowersComponent;
import br.com.rafael.githubuser.followers.di.FollowersModule;
import br.com.rafael.githubuser.followers.presentation.adapter.FollowersAdapter;
import br.com.rafael.githubuser.followers.presentation.data.LeftFollowerClickData;
import br.com.rafael.githubuser.followers.presentation.data.RightFollowerClickData;
import br.com.rafael.githubuser.followers.presentation.listener.OnLeftFollowerClickListener;
import br.com.rafael.githubuser.followers.presentation.listener.OnRightFollowerClickListener;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import br.com.rafael.githubuser.library.di.LibraryComponent;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowersActivity extends BaseActivity implements HasComponent<LibraryComponent>, FollowersContract.View {

    private static final String KEY_STATE = "state";
    private static final String KEY_USERNAME = "key_username";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.state_view)
    MultiStateView stateView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    View errorView;

    @Inject
    FollowersContract.Presenter presenter;

    @Inject
    FollowersAdapter adapter;

    FollowersContract.State state = new FollowersContract.State();

    private OnLeftFollowerClickListener onLeftFollowerClickListener =
            this::handleLeftFollowerClick;

    private OnRightFollowerClickListener onRightFollowerClickListener =
            this::handleRightFollowerClick;

    private void handleLeftFollowerClick(LeftFollowerClickData data) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(data.url()));
        startActivity(intent);
    }

    private void handleRightFollowerClick(RightFollowerClickData data) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(data.url()));
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_STATE, state);
    }

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
                .libraryComponent(getComponent())
                .followersModule(new FollowersModule(this))
                .build()
                .inject(this);
    }

    private void initializeViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter.setLeftFollowerClickListener(onLeftFollowerClickListener);
        adapter.setRightFollowerClickListener(onRightFollowerClickListener);
        stateView.setAnimateLayoutChanges(true);
    }

    private void initializeContents(@Nullable Bundle savedState) {
        boolean shouldInitializeFromState = savedState != null;

        if (shouldInitializeFromState) {
            state = savedState.getParcelable(KEY_STATE);
            presenter.initializeFromState(state);
        } else {
            String username = getIntent().getStringExtra(KEY_USERNAME);
            presenter.initialize(username);
        }
    }

    @Override
    public LibraryComponent getComponent() {
        return GithubUserApplication.get(this).getComponent();
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
    public void showLoadingState() {
        state.isShowingFollowersLoadError = false;
        stateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void showEmptyState() {
        state.isShowingFollowersLoadError = false;
        stateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void showErrorState() {
        state.isShowingFollowersLoadError = true;
        stateView.setViewState(MultiStateView.VIEW_STATE_ERROR);

        if (errorView == null) {
            errorView = stateView.getView(MultiStateView.VIEW_STATE_ERROR);
        }

        errorView.setOnClickListener(view ->
                presenter.initialize(getIntent().getStringExtra(KEY_USERNAME)));
    }

    @Override
    public void showContentState() {
        stateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void showFollowers(FollowersViewModelHolder holder) {
        state.isShowingFollowersLoadError = false;
        state.holder = holder;

        adapter.setData(holder);
        recyclerView.setAdapter(adapter);
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
