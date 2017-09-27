package br.com.rafael.githubuser.followers.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;
import rx.Scheduler;

public class ShowFollowersImpl implements ShowFollowers {

    private Scheduler uiScheduler;
    private FollowersContract.View view;

    @Inject
    public ShowFollowersImpl(@UIScheduler Scheduler uiScheduler,
                             FollowersContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<FollowersViewModelHolder> call(Observable<FollowersViewModelHolder> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showFollowers)
                .doOnError(this::showError)
                .onExceptionResumeNext(Observable.never());
    }

    private void showFollowers(FollowersViewModelHolder holder) {
        if (isEmpty(holder)) {
            view.showEmptyState();
        } else {
            view.showContentState();
            view.showFollowers(holder);
        }
    }

    private void showError(Throwable error) {
        error.printStackTrace();
        view.showErrorState();
    }

    private boolean isEmpty(FollowersViewModelHolder holder) {
        return holder == null || holder.getViewModels() == null || holder.getViewModels().isEmpty();
    }
}
