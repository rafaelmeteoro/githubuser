package br.com.rafael.githubuser.user.domain.interactor;

import rx.Observable;

public interface ShowUserRetryOnError<T> extends Observable.Transformer<T, T> {
}
