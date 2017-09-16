package br.com.rafael.githubuser.core.di.module;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import br.com.rafael.githubuser.core.di.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return activity;
    }

    @Provides
    @PerActivity
    FragmentManager fragmentManager() {
        return activity.getSupportFragmentManager();
    }
}
