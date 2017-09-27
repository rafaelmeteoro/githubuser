package br.com.rafael.githubuser.followers.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.followers.domain.interactor.RestoreFollowersState;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;

public class FollowersRestoreStateCoordinator implements
        Observable.Transformer<FollowersContract.State, FollowersContract.State> {

    private RestoreFollowersState restoreFollowersState;

    @Inject
    public FollowersRestoreStateCoordinator(RestoreFollowersState restoreFollowersState) {
        this.restoreFollowersState = restoreFollowersState;
    }

    @Override
    public Observable<FollowersContract.State> call(Observable<FollowersContract.State> observable) {
        return observable
                .compose(restoreFollowersState);
    }
}
