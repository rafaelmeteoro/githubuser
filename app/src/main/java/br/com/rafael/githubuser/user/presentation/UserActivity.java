package br.com.rafael.githubuser.user.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kennyc.view.MultiStateView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.application.GithubUserApplication;
import br.com.rafael.githubuser.core.di.HasComponent;
import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.core.view.BaseActivity;
import br.com.rafael.githubuser.followers.presentation.FollowersActivity;
import br.com.rafael.githubuser.library.di.LibraryComponent;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.di.DaggerUserComponent;
import br.com.rafael.githubuser.user.di.UserModule;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Scheduler;

public class UserActivity extends BaseActivity implements HasComponent<LibraryComponent>, UserContract.View {

    private static final String KEY_STATE = "state";
    private static final String KEY_USERNAME = "key_username";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.state_view)
    MultiStateView stateView;

    @BindView(R.id.photo_avatar)
    ImageView photoAvatar;

    @BindView(R.id.login_text)
    TextView txtLogin;

    @BindView(R.id.name_text)
    TextView txtName;

    @BindView(R.id.location_text)
    TextView txtLocation;

    @BindView(R.id.button_followers)
    Button btnFollowers;

    View errorView;

    @Inject
    UserContract.Presenter presenter;

    @Inject
    @UIScheduler
    Scheduler uiScheduler;

    UserContract.State state = new UserContract.State();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_STATE, state);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

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
        getSupportActionBar().setTitle(R.string.user_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeViews() {
        stateView.setAnimateLayoutChanges(true);
    }

    private void inject() {
        DaggerUserComponent
                .builder()
                .libraryComponent(getComponent())
                .userModule(new UserModule(this))
                .build()
                .inject(this);
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

    @OnClick(R.id.button_followers)
    public void onClickFollowers() {
        presenter.clickFollowers(getIntent().getStringExtra(KEY_USERNAME));
    }

    @Override
    public void showLoadingState() {
        state.isShowingUserLoadError = false;
        stateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void showEmptySate() {
        state.isShowingUserLoadError = false;
        stateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void showErrorState() {
        state.isShowingUserLoadError = true;
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
    public void showUser(GithubUser githubUser) {
        state.isShowingUserLoadError = false;
        state.githubUser = githubUser;

        txtLogin.setText(githubUser.login());
        txtName.setText(githubUser.name());
        txtLocation.setText(githubUser.location());
        Picasso.with(this)
                .load(githubUser.avatarUrl())
                .into(photoAvatar);
    }

    @Override
    public void launchFollowersActivity(String username) {
        Intent intent = FollowersActivity.IntentBuilder
                .builder(this)
                .username(username)
                .create();
        startActivity(intent);
    }

    public static class IntentBuilder {

        private Intent intent;

        private IntentBuilder(Context context) {
            intent = new Intent(context, UserActivity.class);
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
