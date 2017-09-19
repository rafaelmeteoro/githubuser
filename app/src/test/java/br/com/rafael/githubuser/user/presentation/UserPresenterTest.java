package br.com.rafael.githubuser.user.presentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.core.client.exception.RetrofitException;
import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.GetUser;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserPresenterTest {

    @Mock
    GetUser getUser;

    @Mock
    UserContract.View view;

    @Mock
    AutomaticUnsubscriber automaticUnsubscriber;

    UserContract.Presenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = spy(
                new UserPresenter(
                        getUser,
                        view,
                        automaticUnsubscriber)
        );
    }

    @Test
    public void initializePresenter_shouldDisplayUser() {
        when(getUser.getUser(any()))
                .thenReturn(Observable.just(mock(GithubUser.class)));

        presenter.initialize(any());

        verify(view).showUserLoading();
        verify(view).showUser();
        verify(view).setUser(any());
        verify(view).showPhoto(any());
        verify(view).showLogin(any());
        verify(view).showName(any());
        verify(view).showLocation(any());
    }

    @Test
    public void initializePresenter_shouldDisplayError() {
       when(getUser.getUser(any()))
               .thenReturn(Observable.error(mock(RetrofitException.class)));

        presenter.initialize(any());

        verify(view).showUserLoading();
        verify(view).showUserError();
    }

    @Test
    public void initializeFromStatePresenter_shouldDisplayUser() {
        when(getUser.getUser(any()))
                .thenReturn(Observable.just(mock(GithubUser.class)));

        presenter.initializeFromState(githubUser());
        verify(view).showUser();
        verify(view).setUser(any());
        verify(view).showPhoto(any());
        verify(view).showLogin(any());
        verify(view).showName(any());
        verify(view).showLocation(any());
    }

    private GithubUser githubUser() {
        GithubUser githubUser = new GithubUser();
        githubUser.setAvatarUrl("avatar_url");
        githubUser.setLogin("login");
        githubUser.setName("name");
        githubUser.setLocation("location");
        return githubUser;
    }
}
