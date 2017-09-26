package br.com.rafael.githubuser.followers.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;
import rx.Scheduler;

public class ShowLoadingFollowersImpl implements ShowLoadingFollowers {

    private Scheduler uiScheduler;
    private FollowersContract.View view;

    @Inject
    public ShowLoadingFollowersImpl(@UIScheduler Scheduler uiScheduler,
                                    FollowersContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<String> call(Observable<String> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(ignored -> view.showFollowersLoading());
    }
}
