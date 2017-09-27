package br.com.rafael.githubuser.followers.presentation;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.followers.presentation.coordinator.FollowersCoordinator;
import br.com.rafael.githubuser.followers.presentation.coordinator.FollowersRestoreStateCoordinator;
import rx.Observable;
import rx.Subscription;

public class FollowersPresenter implements FollowersContract.Presenter {

    private FollowersCoordinator followersCoordinator;
    private FollowersRestoreStateCoordinator followersRestoreStateCoordinator;
    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public FollowersPresenter(FollowersCoordinator followersCoordinator,
                              FollowersRestoreStateCoordinator followersRestoreStateCoordinator,
                              AutomaticUnsubscriber automaticUnsubscriber) {
        this.followersCoordinator = followersCoordinator;
        this.followersRestoreStateCoordinator = followersRestoreStateCoordinator;
        this.automaticUnsubscriber = automaticUnsubscriber;
    }

    @Override
    public void initialize(String username) {
        Subscription subscription =
                Observable.just(username)
                        .compose(followersCoordinator)
                        .subscribe();
        automaticUnsubscriber.add(subscription);
    }

    @Override
    public void initializeFromState(FollowersContract.State state) {
        Subscription subscription =
                Observable.just(state)
                        .compose(followersRestoreStateCoordinator)
                        .subscribe();
        automaticUnsubscriber.add(subscription);
    }
}
