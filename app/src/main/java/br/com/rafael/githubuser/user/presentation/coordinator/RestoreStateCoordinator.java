package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.domain.interactor.RestoreUserState;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

public class RestoreStateCoordinator implements
        Observable.Transformer<UserContract.State, UserContract.State> {

    private RestoreUserState restoreUserState;

    @Inject
    public RestoreStateCoordinator(RestoreUserState restoreUserState) {
        this.restoreUserState = restoreUserState;
    }

    @Override
    public Observable<UserContract.State> call(Observable<UserContract.State> observable) {
        return observable
                .compose(restoreUserState);
    }
}
