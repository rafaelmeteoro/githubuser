package br.com.rafael.githubuser.user.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.Scheduler;

public class ShowUserImpl implements ShowUser {

    private Scheduler uiScheduler;
    private UserContract.View view;

    @Inject
    public ShowUserImpl(@UIScheduler Scheduler uiScheduler,
                        UserContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<GithubUser> call(Observable<GithubUser> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showUser);
    }

    private void showUser(GithubUser user) {
        view.showUser();
        view.setUser(user);
        view.showPhoto(user.avatarUrl());
        view.showLogin(user.login());
        view.showName(user.name());
        view.showLocation(user.location());
    }
}
