package br.com.rafael.githubuser.user.di;

import br.com.rafael.githubuser.core.di.PerActivity;
import br.com.rafael.githubuser.library.di.LibraryComponent;
import br.com.rafael.githubuser.user.presentation.UserActivity;
import dagger.Component;

@PerActivity
@Component(
        dependencies = {LibraryComponent.class},
        modules = {UserModule.class}
)
public interface UserComponent {
    void inject(UserActivity activity);
}
