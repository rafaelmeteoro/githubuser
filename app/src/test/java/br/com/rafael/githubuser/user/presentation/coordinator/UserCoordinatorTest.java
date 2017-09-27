package br.com.rafael.githubuser.user.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.GetUser;
import br.com.rafael.githubuser.user.domain.interactor.ShowLoadingUser;
import br.com.rafael.githubuser.user.domain.interactor.ShowUser;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class UserCoordinatorTest {

    @Mock
    ShowLoadingUser showLoadingUser;

    @Mock
    GetUser getUser;

    @Mock
    ShowUser showUser;

    @Mock
    GithubUser githubUser;

    UserCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new UserCoordinator(
                        showLoadingUser,
                        getUser,
                        showUser)
        );
    }

    @Test
    public void callUserCoordinator_shouldExecuteInOrder() {
        String username = "username";

        when(showLoadingUser.call(any()))
                .thenReturn(Observable.just(username));
        when(getUser.call(any()))
                .thenReturn(Observable.just(githubUser));
        when(showUser.call(any()))
                .thenReturn(Observable.just(githubUser));

        InOrder callOrder = inOrder(
                showLoadingUser,
                getUser,
                showUser);

        Observable.just(username)
                .compose(impl)
                .subscribe();

        callOrder.verify(showLoadingUser).call(any());
        callOrder.verify(getUser).call(any());
        callOrder.verify(showUser).call(any());
    }
}
