package br.com.rafael.githubuser.followers.data.repository;

import java.util.List;

import br.com.rafael.githubuser.followers.data.models.Follower;
import rx.Observable;

public interface GithubFollowerRepository {
    Observable<List<Follower>> getFollowers(String username);
}
