package br.com.rafael.githubuser.followers.di;

import br.com.rafael.githubuser.core.di.PerActivity;
import br.com.rafael.githubuser.followers.presentation.FollowersActivity;
import br.com.rafael.githubuser.library.di.LibraryComponent;
import dagger.Component;

@PerActivity
@Component(
        dependencies = {LibraryComponent.class},
        modules = {FollowersModule.class}
)
public interface FollowersComponent {
    void inject(FollowersActivity activity);
}
