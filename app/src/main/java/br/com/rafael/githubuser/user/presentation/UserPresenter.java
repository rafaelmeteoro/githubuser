package br.com.rafael.githubuser.user.presentation;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.user.presentation.coordinator.GetUserCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.RestoreStateCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.ShowLoadingUserCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.ShowUserCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.UserEventBindingCoordinator;
import rx.Observable;
import rx.Subscription;

public class UserPresenter implements UserContract.Presenter {

    private ShowLoadingUserCoordinator showLoadingUserCoordinator;
    private GetUserCoordinator getUserCoordinator;
    private ShowUserCoordinator showUserCoordinator;
    private RestoreStateCoordinator restoreStateCoordinator;
    private AutomaticUnsubscriber automaticUnsubscriber;
    private UserEventBindingCoordinator<String> userEventBindingCoordinator;

    @Inject
    public UserPresenter(ShowLoadingUserCoordinator showLoadingUserCoordinator,
                         GetUserCoordinator getUserCoordinator,
                         ShowUserCoordinator showUserCoordinator,
                         RestoreStateCoordinator restoreStateCoordinator,
                         AutomaticUnsubscriber automaticUnsubscriber,
                         UserEventBindingCoordinator<String> userEventBindingCoordinator) {
        this.showLoadingUserCoordinator = showLoadingUserCoordinator;
        this.getUserCoordinator = getUserCoordinator;
        this.showUserCoordinator = showUserCoordinator;
        this.restoreStateCoordinator = restoreStateCoordinator;
        this.automaticUnsubscriber = automaticUnsubscriber;
        this.userEventBindingCoordinator = userEventBindingCoordinator;
    }

    @Override
    public void initialize(String username) {
        Subscription subscription =
                Observable.just(username)
                        .compose(userEventBindingCoordinator)
                        .compose(showLoadingUserCoordinator)
                        .compose(getUserCoordinator)
                        .compose(showUserCoordinator)
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
}
