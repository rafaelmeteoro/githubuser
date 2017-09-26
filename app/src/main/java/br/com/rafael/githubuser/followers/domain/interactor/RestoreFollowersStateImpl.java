package br.com.rafael.githubuser.followers.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;
import rx.Scheduler;

public class RestoreFollowersStateImpl implements RestoreFollowersState {

    private Scheduler uiScheduler;

    @Inject
    public RestoreFollowersStateImpl(@UIScheduler Scheduler uiScheduler) {
        this.uiScheduler = uiScheduler;
    }

    @Override
    public Observable<FollowersViewModelHolder> call(Observable<FollowersContract.State> observable) {
        return observable
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .map(state -> state.holder);
    }
}
