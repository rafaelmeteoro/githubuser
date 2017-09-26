package br.com.rafael.githubuser.followers.presentation.coordinator;

import javax.inject.Inject;

import br.com.rafael.githubuser.followers.domain.interactor.GetFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowersRetryOnError;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

public class GetFollowersCoordinator implements Observable.Transformer<String, FollowersViewModelHolder> {

    private GetFollowers getFollowers;
    private ShowFollowersRetryOnError<FollowersViewModelHolder> showFollowersRetryOnError;

    @Inject
    public GetFollowersCoordinator(GetFollowers getFollowers,
                                   ShowFollowersRetryOnError<FollowersViewModelHolder> showFollowersRetryOnError) {
        this.getFollowers = getFollowers;
        this.showFollowersRetryOnError = showFollowersRetryOnError;
    }

    @Override
    public Observable<FollowersViewModelHolder> call(Observable<String> observable) {
        return observable
                .compose(getFollowers)
                .compose(showFollowersRetryOnError);
    }
}
