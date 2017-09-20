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
    private GithubUserRepository gitHubApi;

    @Inject
    public GetUserImpl(@IOScheduler Scheduler ioScheduler,
                       @UIScheduler Scheduler uiScheduler,
                       GithubUserRepository gitHubApi) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.gitHubApi = gitHubApi;
    }

    @Override
    public Observable<GithubUser> getUser(String username) {
        return gitHubApi.getUser(username)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler);
    }
}
