package br.com.rafael.githubuser.followers.domain.interactor;

import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

public interface GetFollowers {
    Observable<FollowersViewModelHolder> getFollowers(String username);
}
