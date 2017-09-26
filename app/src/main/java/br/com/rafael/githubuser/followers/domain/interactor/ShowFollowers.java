package br.com.rafael.githubuser.followers.domain.interactor;

import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

public interface ShowFollowers extends Observable.Transformer<FollowersViewModelHolder, FollowersViewModelHolder> {
}
