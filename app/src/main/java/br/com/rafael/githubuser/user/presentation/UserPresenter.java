package br.com.rafael.githubuser.user.presentation;

import android.util.Log;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
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
    public void initialize() {
        Subscription subscription = getUser.getUser()
                .subscribe(githubUser -> {
                    Log.d("UserPresenter", "Ok");
                }, error -> {
                    Log.d("UserPresenter", "NOT OK");
                    error.printStackTrace();
                });
        automaticUnsubscriber.add(subscription);
    }
}
