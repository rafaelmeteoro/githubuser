package br.com.rafael.githubuser.followers.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowers;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

public class ShowFollowersCoordinator implements Observable.Transformer<FollowersViewModelHolder, FollowersViewModelHolder> {

    private ShowFollowers showFollowers;

    @Inject
    public ShowFollowersCoordinator(ShowFollowers showFollowers) {
        this.showFollowers = showFollowers;
    }

    @Override
    public Observable<FollowersViewModelHolder> call(Observable<FollowersViewModelHolder> observable) {
        return observable
                .compose(showFollowers);
    }
}
