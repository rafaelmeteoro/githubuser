package br.com.rafael.githubuser.core.di.module;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ApplicationModule {

    private final Context applicationContext;

    public ApplicationModule(Context applicationContext) {
        this.applicationContext = applicationContext.getApplicationContext();
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    Scheduler providesMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named("jobScheduler")
    Scheduler providesJobScheduler() {
        return Schedulers.io();
    }
}
