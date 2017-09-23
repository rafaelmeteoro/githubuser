package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.Subscription;

public class UserEventBindingCoordinator<T> implements Observable.Transformer<T, T> {

    private ClickFollowersCoordinator clickFollowersCoordinator;
    private SaveStateCoordinator saveStateCoordinator;
    private ShowLoadingUserCoordinator showLoadingUserCoordinator;
    private GetUserCoordinator getUserCoordinator;
    private ShowUserCoordinator showUserCoordinator;

    private AutomaticUnsubscriber automaticUnsubscriber;
    private UserContract.View view;

    @Inject
    public UserEventBindingCoordinator(ClickFollowersCoordinator clickFollowersCoordinator,
                                       SaveStateCoordinator saveStateCoordinator,
                                       ShowLoadingUserCoordinator showLoadingUserCoordinator,
                                       GetUserCoordinator getUserCoordinator,
                                       ShowUserCoordinator showUserCoordinator,
                                       AutomaticUnsubscriber automaticUnsubscriber,
                                       UserContract.View view) {
        this.clickFollowersCoordinator = clickFollowersCoordinator;
        this.saveStateCoordinator = saveStateCoordinator;
        this.showLoadingUserCoordinator = showLoadingUserCoordinator;
        this.getUserCoordinator = getUserCoordinator;
        this.showUserCoordinator = showUserCoordinator;
        this.automaticUnsubscriber = automaticUnsubscriber;
        this.view = view;
    }

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .doOnNext(ignored -> bindAll());
    }

    private void bindAll() {
        bindClickFollowersCoordinator();
        bindSaveStateCoordinator();
        bindOnRetryClickedCoordinator();
    }

    private void bindClickFollowersCoordinator() {
        Subscription subscription = view.onFollowersClicked()
                .compose(clickFollowersCoordinator)
                .subscribe();
        automaticUnsubscriber.add(subscription);
    }

    private void bindSaveStateCoordinator() {
        Subscription subscription = view.onSaveState()
                .compose(saveStateCoordinator)
                .subscribe();
        automaticUnsubscriber.add(subscription);
    }

    private void bindOnRetryClickedCoordinator() {
        Subscription subscription = view.onRetryClicked()
                .compose(showLoadingUserCoordinator)
                .compose(getUserCoordinator)
                .compose(showUserCoordinator)
                .subscribe();
        automaticUnsubscriber.add(subscription);
    }
}
