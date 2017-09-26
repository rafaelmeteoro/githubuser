package br.com.rafael.githubuser.followers.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.followers.domain.interactor.SaveFollowersStateToView;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;

public class FollowersSaveStateCoordinator implements
        Observable.Transformer<FollowersContract.State, FollowersContract.State> {

    private SaveFollowersStateToView saveFollowersStateToView;

    @Inject
    public FollowersSaveStateCoordinator(SaveFollowersStateToView saveFollowersStateToView) {
        this.saveFollowersStateToView = saveFollowersStateToView;
    }

    @Override
    public Observable<FollowersContract.State> call(Observable<FollowersContract.State> observable) {
        return observable
                .compose(saveFollowersStateToView);
    }
}
