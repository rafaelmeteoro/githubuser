package br.com.rafael.githubuser.user.data.repository;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import rx.Observable;

public interface GitHubRepository {
    Observable<GithubUser> getUser(String username);
}
