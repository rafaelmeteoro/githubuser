package br.com.rafael.githubuser.user.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.user.domain.interactor.SaveStateToView;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

public class SaveStateCoordinator implements
        Observable.Transformer<UserContract.State, UserContract.State> {

    private SaveStateToView saveStateToView;

    @Inject
    public SaveStateCoordinator(SaveStateToView saveStateToView) {
        this.saveStateToView = saveStateToView;
    }

    @Override
    public Observable<UserContract.State> call(Observable<UserContract.State> observable) {
        return observable
                .compose(saveStateToView);
    }
}
