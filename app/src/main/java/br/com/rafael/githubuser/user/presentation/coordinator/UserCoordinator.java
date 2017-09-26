package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.GetUser;
import br.com.rafael.githubuser.user.domain.interactor.ShowLoadingUser;
import br.com.rafael.githubuser.user.domain.interactor.ShowUser;
import rx.Observable;

public class UserCoordinator implements Observable.Transformer<String, GithubUser> {

    private ShowLoadingUser showLoadingUser;
    private GetUser getUser;
    private ShowUser showUser;

    @Inject
    public UserCoordinator(ShowLoadingUser showLoadingUser,
                           GetUser getUser,
                           ShowUser showUser) {
        this.showLoadingUser = showLoadingUser;
        this.getUser = getUser;
        this.showUser = showUser;
    }

    @Override
    public Observable<GithubUser> call(Observable<String> observable) {
        return observable
                .compose(showLoadingUser)
                .compose(getUser)
                .compose(showUser);
    }
}
