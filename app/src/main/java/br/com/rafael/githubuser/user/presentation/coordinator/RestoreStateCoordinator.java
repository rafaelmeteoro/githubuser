package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.RestoreUserState;
import br.com.rafael.githubuser.user.domain.interactor.ShowUser;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

public class RestoreStateCoordinator implements
        Observable.Transformer<UserContract.State, GithubUser> {

    private RestoreUserState restoreUserState;
    private ShowUser showUser;
    private UserEventBindingCoordinator<GithubUser> userUserEventBindingCoordinator;

    @Inject
    public RestoreStateCoordinator(RestoreUserState restoreUserState,
                                   ShowUser showUser,
                                   UserEventBindingCoordinator<GithubUser> userUserEventBindingCoordinator) {
        this.restoreUserState = restoreUserState;
        this.showUser = showUser;
        this.userUserEventBindingCoordinator = userUserEventBindingCoordinator;
    }

    @Override
    public Observable<GithubUser> call(Observable<UserContract.State> observable) {
        return observable
                .compose(restoreUserState)
                .compose(showUser)
                .compose(userUserEventBindingCoordinator);
    }
}
