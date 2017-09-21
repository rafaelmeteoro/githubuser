package br.com.rafael.githubuser.followers.presentation;

import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;

public interface FollowersContract {
    interface View {
        void showFollowers(FollowersViewModelHolder holder);

        void showFollowersLoading();

        void showFollowersError();
    }

    interface Presenter {
        void initialize(String username);

        void initializeFromState(FollowersViewModelHolder holder);
    }
}
