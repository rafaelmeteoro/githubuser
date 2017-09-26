package br.com.rafael.githubuser.followers.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.followers.domain.interactor.RestoreFollowersState;
import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowers;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

public class FollowersRestoreStateCoordinator implements
        Observable.Transformer<FollowersContract.State, FollowersViewModelHolder> {

    private RestoreFollowersState restoreFollowersState;
    private ShowFollowers showFollowers;
    private FollowersEventBindingCoordinator<FollowersViewModelHolder> followersEventBindingCoordinator;

    @Inject
    public FollowersRestoreStateCoordinator(RestoreFollowersState restoreFollowersState,
                                            ShowFollowers showFollowers,
                                            FollowersEventBindingCoordinator<FollowersViewModelHolder> followersEventBindingCoordinator) {
        this.restoreFollowersState = restoreFollowersState;
        this.showFollowers = showFollowers;
        this.followersEventBindingCoordinator = followersEventBindingCoordinator;
    }

    @Override
    public Observable<FollowersViewModelHolder> call(Observable<FollowersContract.State> observable) {
        return observable
                .compose(restoreFollowersState)
                .compose(showFollowers)
                .compose(followersEventBindingCoordinator);
    }
}
