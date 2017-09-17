package br.com.rafael.githubuser.user.presentation;

public interface UserContract {
    interface View {
        void showUser();

        void showLogin(String login);

        void showName(String name);

        void showLocation(String location);

        void showUserLoading();

        void showUserError();
    }

    interface Presenter {
        void initialize();
    }
}
