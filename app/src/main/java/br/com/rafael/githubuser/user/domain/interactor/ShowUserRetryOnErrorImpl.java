package br.com.rafael.githubuser.user.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.Scheduler;

public class ShowUserRetryOnErrorImpl<T> implements ShowUserRetryOnError<T> {

    private Scheduler uiScheduler;
    private UserContract.View view;

    @Inject
    public ShowUserRetryOnErrorImpl(@UIScheduler Scheduler uiScheduler,
                                    UserContract.View view) {
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

        view.showUserError();
    }
}
