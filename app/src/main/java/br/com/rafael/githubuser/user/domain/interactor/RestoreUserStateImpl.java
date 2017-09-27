package br.com.rafael.githubuser.user.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.Scheduler;

public class RestoreUserStateImpl implements RestoreUserState {

    private Scheduler uiScheduler;
    private UserContract.View view;

    @Inject
    public RestoreUserStateImpl(@UIScheduler Scheduler uiScheduler,
                                UserContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<UserContract.State> call(Observable<UserContract.State> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showUserIfNotEmptyOrError);
    }

    private void showUserIfNotEmptyOrError(UserContract.State state) {
        if (state.isShowingUserLoadError) {
            view.showErrorState();
        } else if (state.githubUser == null) {
            view.showEmptySate();
        } else {
            view.showContentState();
            view.showUser(state.githubUser);
        }
    }
}
