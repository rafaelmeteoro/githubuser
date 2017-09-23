package br.com.rafael.githubuser.user.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.Scheduler;

public class RestoreUserStateImpl implements RestoreUserState {

    private Scheduler uiScheduler;

    @Inject
    public RestoreUserStateImpl(@UIScheduler Scheduler uiScheduler) {
        this.uiScheduler = uiScheduler;
    }

    @Override
    public Observable<GithubUser> call(Observable<UserContract.State> observable) {
        return observable
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .map(state -> state.githubUser);
    }
}
