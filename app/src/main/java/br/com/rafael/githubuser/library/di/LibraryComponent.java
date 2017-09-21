package br.com.rafael.githubuser.library.di;

import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.githubuser.core.di.module.ApplicationModule;
import br.com.rafael.githubuser.core.di.qualifers.IOScheduler;
import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.followers.data.repository.GithubFollowerRepository;
import br.com.rafael.githubuser.user.data.repository.GithubUserRepository;
import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = {LibraryModule.class, ApplicationModule.class})
public interface LibraryComponent {
    Context context();

    @IOScheduler
    Scheduler ioScheduler();

    @UIScheduler
    Scheduler uiScheduler();

    GithubUserRepository githubUserRepository();

    GithubFollowerRepository githubFollowerRepository();
}
