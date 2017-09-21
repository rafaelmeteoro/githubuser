package br.com.rafael.githubuser.followers.di;

import android.arch.lifecycle.LifecycleRegistryOwner;

import br.com.rafael.githubuser.core.di.PerActivity;
import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.core.lifecycle.LifecycleUnsubscriber;
import br.com.rafael.githubuser.followers.domain.interactor.GetFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.GetFollowersImpl;
import br.com.rafael.githubuser.followers.presentation.FollowersActivity;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import br.com.rafael.githubuser.followers.presentation.FollowersPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class FollowersModule {

    private FollowersActivity activity;

    public FollowersModule(FollowersActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    FollowersContract.View view() {
        return activity;
    }

    @Provides
    @PerActivity
    FollowersContract.Presenter presenter(FollowersPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LifecycleRegistryOwner lifecycleRegistryOwner() {
        return activity;
    }

    @Provides
    @PerActivity
    AutomaticUnsubscriber automaticUnsubscriber(LifecycleUnsubscriber impl) {
        return impl;
    }

    @Provides
    @PerActivity
    GetFollowers getFollowers(GetFollowersImpl impl) {
        return impl;
    }
}
