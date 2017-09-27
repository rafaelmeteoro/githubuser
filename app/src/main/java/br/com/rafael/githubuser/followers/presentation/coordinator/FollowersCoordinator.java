package br.com.rafael.githubuser.followers.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.followers.domain.interactor.GetFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.ShowLoadingFollowers;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

public class FollowersCoordinator implements Observable.Transformer<String, FollowersViewModelHolder> {

    private ShowLoadingFollowers showLoadingFollowers;
    private GetFollowers getFollowers;
    private ShowFollowers showFollowers;

    @Inject
    public FollowersCoordinator(ShowLoadingFollowers showLoadingFollowers,
                                GetFollowers getFollowers,
                                ShowFollowers showFollowers) {
        this.showLoadingFollowers = showLoadingFollowers;
        this.getFollowers = getFollowers;
        this.showFollowers = showFollowers;
    }

    @Override
    public Observable<FollowersViewModelHolder> call(Observable<String> observable) {
        return observable
                .compose(showLoadingFollowers)
                .compose(getFollowers)
                .compose(showFollowers);
    }
}
