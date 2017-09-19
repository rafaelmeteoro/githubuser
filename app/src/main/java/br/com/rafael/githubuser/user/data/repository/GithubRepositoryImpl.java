package br.com.rafael.githubuser.user.data.repository;

import br.com.rafael.githubuser.core.client.ApiClientUtil;
import br.com.rafael.githubuser.core.client.AuthenticationType;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import rx.Observable;

public class GithubRepositoryImpl implements GitHubRepository {

    private GithubUserApi api;

    public GithubRepositoryImpl(String host) {
        api = new ApiClientUtil.Builder()
                .authentication(AuthenticationType.DIGEST)
                .log(true)
                .url(host)
                .build()
                .create(GithubUserApi.class);
    }

    @Override
    public Observable<GithubUser> getUser(String username) {
        return api.getUser(username);
    }
}
