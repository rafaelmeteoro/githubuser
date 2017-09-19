package br.com.rafael.githubuser.user.presentation;

import br.com.rafael.githubuser.user.data.models.GithubUser;

public interface UserContract {
    interface View {
        void showUser();

        void setUser(GithubUser githubUser);

        void showPhoto(String photoUrl);

        void showLogin(String login);

        void showName(String name);

        void showLocation(String location);

        void showUserLoading();

        void showUserError();
    }

    interface Presenter {
        void initialize(String username);

        void initializeFromState(GithubUser githubUser);
    }
}
