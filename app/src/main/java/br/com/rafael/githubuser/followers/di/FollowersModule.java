package br.com.rafael.githubuser.followers.di;

import android.arch.lifecycle.LifecycleOwner;

import br.com.rafael.githubuser.core.di.PerActivity;
import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.core.lifecycle.LifecycleUnsubscriber;
import br.com.rafael.githubuser.followers.domain.interactor.GetFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.GetFollowersImpl;
import br.com.rafael.githubuser.followers.domain.interactor.RestoreFollowersState;
import br.com.rafael.githubuser.followers.domain.interactor.RestoreFollowersStateImpl;
import br.com.rafael.githubuser.followers.domain.interactor.SaveFollowersStateToView;
import br.com.rafael.githubuser.followers.domain.interactor.SaveFollowersStateToViewImpl;
import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowersImpl;
import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowersRetryOnError;
import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowersRetryOnErrorImpl;
import br.com.rafael.githubuser.followers.domain.interactor.ShowLoadingFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.ShowLoadingFollowersImpl;
import br.com.rafael.githubuser.followers.presentation.FollowersActivity;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import br.com.rafael.githubuser.followers.presentation.FollowersPresenter;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
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
    ShowLoadingFollowers showLoadingFollowers(ShowLoadingFollowersImpl impl) {
        return impl;
    }

    @Provides
    @PerActivity
    GetFollowers getFollowers(GetFollowersImpl impl) {
        return impl;
    }

    @Provides
    @PerActivity
    ShowFollowers showFollowers(ShowFollowersImpl impl) {
        return impl;
    }

    @Provides
    @PerActivity
    RestoreFollowersState restoreFollowersState(RestoreFollowersStateImpl impl) {
        return impl;
    }

    @Provides
    @PerActivity
    SaveFollowersStateToView saveFollowersStateToView(SaveFollowersStateToViewImpl impl) {
        return impl;
    }

    @Provides
    @PerActivity
    ShowFollowersRetryOnError<FollowersViewModelHolder> showFollowersRetryOnError(
            ShowFollowersRetryOnErrorImpl<FollowersViewModelHolder> impl) {
        return impl;
    }
}
