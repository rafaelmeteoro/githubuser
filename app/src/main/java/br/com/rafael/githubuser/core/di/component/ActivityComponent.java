package br.com.rafael.githubuser.core.di.component;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import br.com.rafael.githubuser.core.di.PerActivity;
import br.com.rafael.githubuser.core.di.module.ActivityModule;
import dagger.Component;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {
    AppCompatActivity activity();

    FragmentManager fragmentManager();
}
