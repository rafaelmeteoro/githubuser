package br.com.rafael.githubuser.user.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.IOScheduler;
import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.data.repository.GithubUserRepository;
import rx.Observable;
import rx.Scheduler;

public class GetUserImpl implements GetUser {

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;
    private GithubUserRepository githubUserRepository;

    @Inject
    public GetUserImpl(@IOScheduler Scheduler ioScheduler,
                       @UIScheduler Scheduler uiScheduler,
                       GithubUserRepository githubUserRepository) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.githubUserRepository = githubUserRepository;
    }

    @Override
    public Observable<GithubUser> call(Observable<String> observable) {
        return observable
                .flatMap(this::getUserFromRepository);
    }

    private Observable<GithubUser> getUserFromRepository(String username) {
        return githubUserRepository.getUser(username)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler);
    }
}
