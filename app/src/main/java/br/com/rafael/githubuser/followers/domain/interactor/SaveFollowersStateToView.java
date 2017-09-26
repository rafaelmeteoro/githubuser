package br.com.rafael.githubuser.followers.domain.interactor;

import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;

public interface SaveFollowersStateToView
        extends Observable.Transformer<FollowersContract.State, FollowersContract.State> {
}
