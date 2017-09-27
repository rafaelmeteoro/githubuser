package br.com.rafael.githubuser.followers.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;
import rx.Scheduler;

public class RestoreFollowersStateImpl implements RestoreFollowersState {

    private Scheduler uiScheduler;
    private FollowersContract.View view;

    @Inject
    public RestoreFollowersStateImpl(@UIScheduler Scheduler uiScheduler,
                                     FollowersContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<FollowersContract.State> call(Observable<FollowersContract.State> observable) {
        return observable
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .doOnNext(this::showFollowersIfNotEmptyOrError);
    }

    private void showFollowersIfNotEmptyOrError(FollowersContract.State state) {
        if (state.isShowingFollowersLoadError) {
            view.showErrorState();
        } else if (isEmpty(state.holder)) {
            view.showEmptyState();
        } else {
            view.showContentState();
            view.showFollowers(state.holder);
        }
    }

    private boolean isEmpty(FollowersViewModelHolder holder) {
        return holder == null || holder.getViewModels() == null || holder.getViewModels().isEmpty();
    }
}
