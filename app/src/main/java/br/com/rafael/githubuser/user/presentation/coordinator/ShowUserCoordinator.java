package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.ShowUser;
import rx.Observable;

public class ShowUserCoordinator implements Observable.Transformer<GithubUser, GithubUser> {

    private ShowUser showUser;

    @Inject
    public ShowUserCoordinator(ShowUser showUser) {
        this.showUser = showUser;
    }

    @Override
    public Observable<GithubUser> call(Observable<GithubUser> observable) {
        return observable
                .compose(showUser);
    }
}
