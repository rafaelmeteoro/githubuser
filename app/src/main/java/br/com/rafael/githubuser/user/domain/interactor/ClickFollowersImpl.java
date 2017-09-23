package br.com.rafael.githubuser.user.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.Scheduler;

public class ClickFollowersImpl implements ClickFollowers {

    private Scheduler uiScheduler;
    private UserContract.View view;

    @Inject
    public ClickFollowersImpl(@UIScheduler Scheduler uiScheduler,
                              UserContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<String> call(Observable<String> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(view::callFollowers);
    }
}
