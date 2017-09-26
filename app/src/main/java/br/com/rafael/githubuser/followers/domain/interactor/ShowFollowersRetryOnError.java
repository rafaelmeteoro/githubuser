package br.com.rafael.githubuser.followers.domain.interactor;

import rx.Observable;

public interface ShowFollowersRetryOnError<T> extends Observable.Transformer<T, T> {
}
