package br.com.rafael.githubuser.user.data.repository;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubUserApi {
    @GET("users/{username}")
    Observable<GithubUser> getUser(
            @Path("username") String username);
}
