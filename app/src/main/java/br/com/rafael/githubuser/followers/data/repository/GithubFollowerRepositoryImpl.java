package br.com.rafael.githubuser.followers.data.repository;

import java.util.List;

import br.com.rafael.githubuser.core.client.ApiClientUtil;
import br.com.rafael.githubuser.core.client.AuthenticationType;
import br.com.rafael.githubuser.followers.data.models.Follower;
import rx.Observable;

public class GithubFollowerRepositoryImpl implements GithubFollowerRepository {

    private GithubFollowerApi api;

    public GithubFollowerRepositoryImpl(String host) {
        api = new ApiClientUtil.Builder()
                .authentication(AuthenticationType.DIGEST)
                .log(true)
                .url(host)
                .build()
                .create(GithubFollowerApi.class);
    }

    @Override
    public Observable<List<Follower>> getFollowers(String username) {
        return api.getFollowers(username);
    }
}
