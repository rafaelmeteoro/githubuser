package br.com.rafael.githubuser.user.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.kennyc.view.MultiStateView;

import javax.inject.Inject;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.application.GithubUserApplication;
import br.com.rafael.githubuser.core.view.BaseActivity;
import br.com.rafael.githubuser.user.di.DaggerUserComponent;
import br.com.rafael.githubuser.user.di.UserModule;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends BaseActivity implements UserContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.state_view)
    MultiStateView stateView;

    @BindView(R.id.text_login)
    TextView txtLogin;

    @BindView(R.id.text_name)
    TextView txtName;

    @BindView(R.id.text_location)
    TextView txtLocation;

    @Inject
    UserContract.Presenter presenter;

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

        } else {
            presenter.initialize();
        }
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
}
