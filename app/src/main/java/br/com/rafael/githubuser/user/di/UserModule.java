package br.com.rafael.githubuser.user.di;

import android.arch.lifecycle.LifecycleOwner;

import br.com.rafael.githubuser.core.di.PerActivity;
import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.core.lifecycle.LifecycleUnsubscriber;
import br.com.rafael.githubuser.user.domain.interactor.GetUser;
import br.com.rafael.githubuser.user.domain.interactor.GetUserImpl;
import br.com.rafael.githubuser.user.presentation.UserActivity;
import br.com.rafael.githubuser.user.presentation.UserContract;
import br.com.rafael.githubuser.user.presentation.UserPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    private UserActivity activity;

    public UserModule(UserActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    UserContract.View view() {
        return activity;
    }

    @Provides
    @PerActivity
    UserContract.Presenter presenter(UserPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LifecycleOwner lifecycleOwner() {
        return activity;
    }

    @Provides
    @PerActivity
    AutomaticUnsubscriber automaticUnsubscriber(LifecycleUnsubscriber impl) {
        return impl;
    }

    @Provides
    @PerActivity
    GetUser getUser(GetUserImpl impl) {
        return impl;
    }
}
