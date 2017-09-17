package br.com.rafael.githubuser.user.domain.interactor;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.IOScheduler;
import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.data.repository.GitHubRepository;
import rx.Observable;
import rx.Scheduler;

public class GetUserImpl implements GetUser {

    private static final String GITHUB_USERNAME = "rafaelmeteoro";

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;
    private GitHubRepository gitHubApi;

    @Inject
    public GetUserImpl(@IOScheduler Scheduler ioScheduler,
                       @UIScheduler Scheduler uiScheduler,
                       GitHubRepository gitHubApi) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.gitHubApi = gitHubApi;
    }

    @Override
    public Observable<GithubUser> getUser() {
        return gitHubApi.getUser(GITHUB_USERNAME)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler);
    }
}
