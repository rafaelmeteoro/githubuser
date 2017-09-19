package br.com.rafael.githubuser.library.di;

import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.user.data.repository.GitHubRepository;
import br.com.rafael.githubuser.user.data.repository.GithubRepositoryImpl;
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
    GitHubRepository providesGitHubRepository() {
        return new GithubRepositoryImpl(context.getString(R.string.api_host));
    }
}
