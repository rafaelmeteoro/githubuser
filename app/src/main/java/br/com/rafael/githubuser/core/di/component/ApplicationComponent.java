package br.com.rafael.githubuser.core.di.component;

import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.githubuser.core.di.module.ApplicationModule;
import br.com.rafael.githubuser.core.di.qualifers.IOScheduler;
import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.core.view.BaseActivity;
import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity activity);

    Context context();

    @UIScheduler
    Scheduler mainThreadScheduler();

    @IOScheduler
    Scheduler jobScheduler();
}
