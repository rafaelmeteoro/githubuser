package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.domain.interactor.ShowLoadingUser;
import rx.Observable;

public class ShowLoadingUserCoordinator implements Observable.Transformer<String, String> {

    private ShowLoadingUser showLoadingUser;

    @Inject
    public ShowLoadingUserCoordinator(ShowLoadingUser showLoadingUser) {
        this.showLoadingUser = showLoadingUser;
    }

    @Override
    public Observable<String> call(Observable<String> observable) {
        return observable
                .compose(showLoadingUser);
    }
}
