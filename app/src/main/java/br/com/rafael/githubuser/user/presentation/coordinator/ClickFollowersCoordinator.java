package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.domain.interactor.ClickFollowers;
import rx.Observable;

public class ClickFollowersCoordinator implements Observable.Transformer<String, String> {

    private ClickFollowers clickFollowers;

    @Inject
    public ClickFollowersCoordinator(ClickFollowers clickFollowers) {
        this.clickFollowers = clickFollowers;
    }

    @Override
    public Observable<String> call(Observable<String> observable) {
        return observable
                .compose(clickFollowers);
    }
}
