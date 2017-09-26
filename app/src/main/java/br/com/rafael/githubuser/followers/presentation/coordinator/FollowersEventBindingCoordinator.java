package br.com.rafael.githubuser.followers.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;
import rx.Subscription;

public class FollowersEventBindingCoordinator<T> implements Observable.Transformer<T, T> {

    private FollowersSaveStateCoordinator followersSaveStateCoordinator;
    private ShowLoadingFollowersCoordinator showLoadingFollowersCoordinator;
    private GetFollowersCoordinator getFollowersCoordinator;
    private ShowFollowersCoordinator showFollowersCoordinator;

    private AutomaticUnsubscriber automaticUnsubscriber;
    private FollowersContract.View view;

    @Inject
    public FollowersEventBindingCoordinator(FollowersSaveStateCoordinator followersSaveStateCoordinator,
                                            ShowLoadingFollowersCoordinator showLoadingFollowersCoordinator,
                                            GetFollowersCoordinator getFollowersCoordinator,
                                            ShowFollowersCoordinator showFollowersCoordinator,
                                            AutomaticUnsubscriber automaticUnsubscriber,
                                            FollowersContract.View view) {
        this.followersSaveStateCoordinator = followersSaveStateCoordinator;
        this.showLoadingFollowersCoordinator = showLoadingFollowersCoordinator;
        this.getFollowersCoordinator = getFollowersCoordinator;
        this.showFollowersCoordinator = showFollowersCoordinator;
        this.automaticUnsubscriber = automaticUnsubscriber;
        this.view = view;
    }

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable.
                doOnNext(ignored -> bindAll());
    }

    private void bindAll() {
        bindSaveStateCoordinator();
        bindOnRetryClickedCoordinator();
    }

    private void bindSaveStateCoordinator() {
        Subscription subscription = view.onSaveState()
                .compose(followersSaveStateCoordinator)
                .subscribe();
        automaticUnsubscriber.add(subscription);
    }

    private void bindOnRetryClickedCoordinator() {
        Subscription subscription = view.onRetryClicked()
                .compose(showLoadingFollowersCoordinator)
                .compose(getFollowersCoordinator)
                .compose(showFollowersCoordinator)
                .subscribe();
        automaticUnsubscriber.add(subscription);
    }
}
