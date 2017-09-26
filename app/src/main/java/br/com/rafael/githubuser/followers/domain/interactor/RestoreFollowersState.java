package br.com.rafael.githubuser.followers.domain.interactor;

import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

public interface RestoreFollowersState extends
        Observable.Transformer<FollowersContract.State, FollowersViewModelHolder> {
}
