package br.com.rafael.githubuser.followers.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;

public class SaveFollowersStateToViewImpl implements SaveFollowersStateToView {

    private FollowersContract.View view;

    @Inject
    public SaveFollowersStateToViewImpl(FollowersContract.View view) {
        this.view = view;
    }

    @Override
    public Observable<FollowersContract.State> call(Observable<FollowersContract.State> observable) {
        return observable
                .doOnNext(view::saveState);
    }
}
