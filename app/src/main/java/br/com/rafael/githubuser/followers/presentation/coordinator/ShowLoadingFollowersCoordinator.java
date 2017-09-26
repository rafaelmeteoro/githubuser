package br.com.rafael.githubuser.followers.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.followers.domain.interactor.ShowLoadingFollowers;
import rx.Observable;

public class ShowLoadingFollowersCoordinator implements Observable.Transformer<String, String> {

    private ShowLoadingFollowers showLoadingFollowers;

    @Inject
    public ShowLoadingFollowersCoordinator(ShowLoadingFollowers showLoadingFollowers) {
        this.showLoadingFollowers = showLoadingFollowers;
    }

    @Override
    public Observable<String> call(Observable<String> observable) {
        return observable
                .compose(showLoadingFollowers);
    }
}
