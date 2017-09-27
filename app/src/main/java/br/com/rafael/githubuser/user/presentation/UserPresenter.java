package br.com.rafael.githubuser.user.presentation;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.user.presentation.coordinator.ClickFollowersCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.RestoreStateCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.UserCoordinator;
import rx.Observable;
import rx.Subscription;

public class UserPresenter implements UserContract.Presenter {

    private UserCoordinator userCoordinator;
    private RestoreStateCoordinator restoreStateCoordinator;
    private ClickFollowersCoordinator clickFollowersCoordinator;
    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public UserPresenter(UserCoordinator userCoordinator,
                         RestoreStateCoordinator restoreStateCoordinator,
                         ClickFollowersCoordinator clickFollowersCoordinator,
                         AutomaticUnsubscriber automaticUnsubscriber) {
        this.userCoordinator = userCoordinator;
        this.restoreStateCoordinator = restoreStateCoordinator;
        this.clickFollowersCoordinator = clickFollowersCoordinator;
        this.automaticUnsubscriber = automaticUnsubscriber;
    }

    @Override
    public void initialize(String username) {
        Subscription subscription =
                Observable.just(username)
                        .compose(userCoordinator)
                        .subscribe();
        automaticUnsubscriber.add(subscription);
    }

    @Override
    public void initializeFromState(UserContract.State state) {
        Subscription subscription =
                Observable.just(state)
                        .compose(restoreStateCoordinator)
                        .subscribe();
        automaticUnsubscriber.add(subscription);
    }

    @Override
    public void clickFollowers(String username) {
        Subscription subscription =
                Observable.just(username)
                        .compose(clickFollowersCoordinator)
                        .subscribe();
        automaticUnsubscriber.add(subscription);
    }
}
