package br.com.rafael.githubuser.user.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.domain.interactor.RestoreUserState;
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
    UserContract.State state;

    RestoreStateCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new RestoreStateCoordinator(
                        restoreUserState)
        );
    }

    @Test
    public void callCoordinatorRestoreState_shouldExecuteInOrder() {
        when(restoreUserState.call(any()))
                .thenReturn(Observable.just(state));

        InOrder callOrder = inOrder(
                restoreUserState);

        Observable.just(state)
                .compose(impl)
                .subscribe();

        callOrder.verify(restoreUserState).call(any());
    }
}
