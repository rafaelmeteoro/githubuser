package br.com.rafael.githubuser.user.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kennyc.view.MultiStateView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.application.GithubUserApplication;
import br.com.rafael.githubuser.core.view.BaseActivity;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.di.DaggerUserComponent;
import br.com.rafael.githubuser.user.di.UserModule;
import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

public class UserActivity extends BaseActivity implements UserContract.View {

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

    @Inject
    UserContract.Presenter presenter;

    @State
    GithubUser githubUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        bindViews();
        setUpToolbar();
        inject();
        initializeContents(savedInstanceState);
    }

    private void bindViews() {
        ButterKnife.bind(this);
        stateView.setAnimateLayoutChanges(true);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.user_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inject() {
        DaggerUserComponent
                .builder()
                .libraryComponent(GithubUserApplication.get(this).getComponent())
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }

    private void initializeContents(@Nullable Bundle savedState) {
        boolean shouldInitializeFromState = savedState != null;

        if (shouldInitializeFromState) {
            presenter.initializeFromState(githubUser);
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
    public void setUser(GithubUser githubUser) {
        this.githubUser = githubUser;
    }

    @Override
    public void showUser() {
        stateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void showUserLoading() {
        stateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void showUserError() {
        stateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void showPhoto(String photoUrl) {
        Picasso.with(this)
                .load(photoUrl)
                .into(photoAvatar);
    }

    @Override
    public void showLogin(String login) {
        txtLogin.setText(login);
    }

    @Override
    public void showName(String name) {
        txtName.setText(name);
    }

    @Override
    public void showLocation(String location) {
        txtLocation.setText(location);
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
