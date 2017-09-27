package br.com.rafael.githubuser.user.domain.interactor;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import rx.Observable;

public interface ShowUser extends Observable.Transformer<GithubUser, GithubUser> {
}
