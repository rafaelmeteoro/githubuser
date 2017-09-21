package br.com.rafael.githubuser.followers.presentation;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.followers.domain.interactor.GetFollowers;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Subscription;

public class FollowersPresenter implements FollowersContract.Presenter {

    private GetFollowers getFollowers;
    private FollowersContract.View view;
    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public FollowersPresenter(GetFollowers getFollowers,
                              FollowersContract.View view,
                              AutomaticUnsubscriber automaticUnsubscriber) {
        this.getFollowers = getFollowers;
        this.view = view;
        this.automaticUnsubscriber = automaticUnsubscriber;
    }

    @Override
    public void initialize(String username) {
        view.showFollowersLoading();

        Subscription subscription = getFollowers.getFollowers(username)
                .subscribe(holder -> view.showFollowers(holder)
                        , error -> {
                            view.showFollowersError();
                            error.printStackTrace();
                        });
        automaticUnsubscriber.add(subscription);
    }

    @Override
    public void initializeFromState(FollowersViewModelHolder holder) {
        view.showFollowers(holder);
    }
}
