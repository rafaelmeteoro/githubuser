package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.GetUser;
import br.com.rafael.githubuser.user.domain.interactor.ShowUserRetryOnError;
import rx.Observable;

public class GetUserCoordinator implements Observable.Transformer<String, GithubUser> {

    private GetUser getUser;
    private ShowUserRetryOnError<GithubUser> showUserRetryOnError;

    @Inject
    public GetUserCoordinator(GetUser getUser,
                              ShowUserRetryOnError<GithubUser> showUserRetryOnError) {
        this.getUser = getUser;
        this.showUserRetryOnError = showUserRetryOnError;
    }

    @Override
    public Observable<GithubUser> call(Observable<String> observable) {
        return observable
                .compose(getUser)
                .compose(showUserRetryOnError);
    }
}
