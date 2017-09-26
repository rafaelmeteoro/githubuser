package br.com.rafael.githubuser.followers.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;
import rx.Scheduler;

public class ShowFollowersRetryOnErrorImpl<T> implements ShowFollowersRetryOnError<T> {

    private Scheduler uiScheduler;
    private FollowersContract.View view;

    @Inject
    public ShowFollowersRetryOnErrorImpl(@UIScheduler Scheduler uiScheduler,
                                         FollowersContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .subscribeOn(uiScheduler)
                .doOnError(this::onError)
                .onExceptionResumeNext(Observable.never());
    }

    private void onError(Throwable error) {
        error.printStackTrace();

        view.showFollowersError();
    }
}
