package br.com.rafael.githubuser.library.di;

import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.followers.data.repository.GithubFollowerRepository;
import br.com.rafael.githubuser.followers.data.repository.GithubFollowerRepositoryImpl;
import br.com.rafael.githubuser.user.data.repository.GithubUserRepository;
import br.com.rafael.githubuser.user.data.repository.GithubUserRepositoryImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class LibraryModule {

    private final Context context;

    public LibraryModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    GithubUserRepository providesGitHubRepository() {
        return new GithubUserRepositoryImpl(context.getString(R.string.api_host));
    }

    @Provides
    @Singleton
    GithubFollowerRepository providesGithubFollowerRepository() {
        return new GithubFollowerRepositoryImpl(context.getString(R.string.api_host));
    }
}
