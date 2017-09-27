package br.com.rafael.githubuser.user.domain.interactor;

import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

public interface RestoreUserState extends
        Observable.Transformer<UserContract.State, UserContract.State> {
}
