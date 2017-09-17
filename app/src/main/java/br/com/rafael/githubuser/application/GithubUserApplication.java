package br.com.rafael.githubuser.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import br.com.rafael.githubuser.core.di.module.ApplicationModule;
import br.com.rafael.githubuser.library.di.DaggerLibraryComponent;
import br.com.rafael.githubuser.library.di.LibraryComponent;
import br.com.rafael.githubuser.library.di.LibraryModule;

public class GithubUserApplication extends Application {

    private LibraryComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        initDagger();
    }

    private void initDagger() {
        component = DaggerLibraryComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .libraryModule(new LibraryModule(this))
                .build();
    }

    public LibraryComponent getComponent() {
        return component;
    }

    public static GithubUserApplication get(Context context) {
        return (GithubUserApplication) context.getApplicationContext();
    }
}
