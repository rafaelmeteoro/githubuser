package br.com.rafael.githubuser.user.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.RestoreUserState;
import br.com.rafael.githubuser.user.domain.interactor.ShowUser;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class RestoreStateCoordinatorTest {

    @Mock
    RestoreUserState restoreUserState;

    @Mock
    ShowUser showUser;

    @Mock
    UserEventBindingCoordinator<GithubUser> userUserEventBindingCoordinator;

    @Mock
    UserContract.State state;

    @Mock
    GithubUser githubUser;

    RestoreStateCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new RestoreStateCoordinator(
                        restoreUserState,
                        showUser,
                        userUserEventBindingCoordinator)
        );
    }

    @Test
    public void callCoordinatorRestoreState_shouldExecuteInOrder() {
        when(restoreUserState.call(any()))
                .thenReturn(Observable.just(githubUser));
        when(showUser.call(any()))
                .thenReturn(Observable.just(githubUser));
        when(userUserEventBindingCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));

        InOrder callOrder = inOrder(
                restoreUserState,
                showUser,
                userUserEventBindingCoordinator);

        Observable.just(state)
                .compose(impl)
                .subscribe();

        callOrder.verify(restoreUserState).call(any());
        callOrder.verify(showUser).call(any());
        callOrder.verify(userUserEventBindingCoordinator).call(any());
    }
}
