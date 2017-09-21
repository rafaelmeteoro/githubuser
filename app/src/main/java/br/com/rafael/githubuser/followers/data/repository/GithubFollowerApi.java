package br.com.rafael.githubuser.followers.data.repository;

import java.util.List;

import br.com.rafael.githubuser.followers.data.models.Follower;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubFollowerApi {
    @GET("users/{username}/followers")
    Observable<List<Follower>> getFollowers(
            @Path("username") String username);
}
