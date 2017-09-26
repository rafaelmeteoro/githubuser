package br.com.rafael.githubuser.user.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

public class SaveStateToViewImpl implements SaveStateToView {

    private UserContract.View view;

    @Inject
    public SaveStateToViewImpl(UserContract.View view) {
        this.view = view;
    }

    @Override
    public Observable<UserContract.State> call(Observable<UserContract.State> observable) {
        return observable;
    }
}
