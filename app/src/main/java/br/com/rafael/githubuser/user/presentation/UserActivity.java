package br.com.rafael.githubuser.user.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.application.GithubUserApplication;
import br.com.rafael.githubuser.core.view.BaseActivity;
import br.com.rafael.githubuser.user.di.DaggerUserComponent;
import br.com.rafael.githubuser.user.di.UserModule;

public class UserActivity extends BaseActivity implements UserContract.View {

    @Inject
    UserContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        inject();
        initializeContents(savedInstanceState);
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
}
