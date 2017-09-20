package br.com.rafael.githubuser.followers.presentation;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;

public class FollowersPresenter implements FollowersContract.Presenter {

    private FollowersContract.View view;
    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public FollowersPresenter(FollowersContract.View view,
                              AutomaticUnsubscriber automaticUnsubscriber) {
        this.view = view;
        this.automaticUnsubscriber = automaticUnsubscriber;
    }
}
