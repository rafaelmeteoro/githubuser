package br.com.rafael.githubuser.user.presentation;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.GetUser;
import rx.Subscription;

public class UserPresenter implements UserContract.Presenter {

    private GetUser getUser;
    private UserContract.View view;
    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public UserPresenter(GetUser getUser,
                         UserContract.View view,
                         AutomaticUnsubscriber automaticUnsubscriber) {
        this.getUser = getUser;
        this.view = view;
        this.automaticUnsubscriber = automaticUnsubscriber;
    }

    @Override
    public void initialize(String username) {
        view.showUserLoading();

        Subscription subscription = getUser.getUser(username)
                .subscribe(this::setGithubUser,
                        error -> {
                            view.showUserError();
                            error.printStackTrace();
                        });
        automaticUnsubscriber.add(subscription);
    }

    @Override
    public void initializeFromState(GithubUser githubUser) {
        setGithubUser(githubUser);
    }

    private void setGithubUser(GithubUser githubUser) {
        view.showUser();
        view.setUser(githubUser);
        view.showPhoto(githubUser.getAvatarUrl());
        view.showLogin(githubUser.getLogin());
        view.showName(githubUser.getName());
        view.showLocation(githubUser.getLocation());
    }
}
