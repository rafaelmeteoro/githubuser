package br.com.rafael.githubuser.followers.presentation;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.followers.presentation.coordinator.FollowersEventBindingCoordinator;
import br.com.rafael.githubuser.followers.presentation.coordinator.FollowersRestoreStateCoordinator;
import br.com.rafael.githubuser.followers.presentation.coordinator.GetFollowersCoordinator;
import br.com.rafael.githubuser.followers.presentation.coordinator.ShowFollowersCoordinator;
import br.com.rafael.githubuser.followers.presentation.coordinator.ShowLoadingFollowersCoordinator;
import rx.Observable;
import rx.Subscription;

public class FollowersPresenter implements FollowersContract.Presenter {

    private ShowLoadingFollowersCoordinator showLoadingFollowersCoordinator;
    private GetFollowersCoordinator getFollowersCoordinator;
    private ShowFollowersCoordinator showFollowersCoordinator;
    private FollowersRestoreStateCoordinator followersRestoreStateCoordinator;
    private AutomaticUnsubscriber automaticUnsubscriber;
    private FollowersEventBindingCoordinator<String> followersEventBindingCoordinator;

    @Inject
    public FollowersPresenter(ShowLoadingFollowersCoordinator showLoadingFollowersCoordinator,
                              GetFollowersCoordinator getFollowersCoordinator,
                              ShowFollowersCoordinator showFollowersCoordinator,
                              FollowersRestoreStateCoordinator followersRestoreStateCoordinator,
                              AutomaticUnsubscriber automaticUnsubscriber,
                              FollowersEventBindingCoordinator<String> followersEventBindingCoordinator) {
        this.showLoadingFollowersCoordinator = showLoadingFollowersCoordinator;
        this.getFollowersCoordinator = getFollowersCoordinator;
        this.showFollowersCoordinator = showFollowersCoordinator;
        this.followersRestoreStateCoordinator = followersRestoreStateCoordinator;
        this.automaticUnsubscriber = automaticUnsubscriber;
        this.followersEventBindingCoordinator = followersEventBindingCoordinator;
    }

    @Override
    public void initialize(String username) {
        Subscription subscription =
                Observable.just(username)
                        .compose(followersEventBindingCoordinator)
                        .compose(showLoadingFollowersCoordinator)
                        .compose(getFollowersCoordinator)
                        .compose(showFollowersCoordinator)
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
