package br.com.rafael.githubuser.followers.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.followers.domain.interactor.RestoreFollowersState;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class FollowersRestoreStateCoordinatorTest {

    @Mock
    RestoreFollowersState restoreFollowersState;

    @Mock
    FollowersContract.State state;

    FollowersRestoreStateCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new FollowersRestoreStateCoordinator(
                        restoreFollowersState)
        );
    }

    @Test
    public void callCoordinatorRestoreState_shouldExecuteInOrder() {
        when(restoreFollowersState.call(any()))
                .thenReturn(Observable.just(state));

        InOrder callOrder = inOrder(
                restoreFollowersState
        );

        Observable.just(state)
                .compose(impl)
                .subscribe();

        callOrder.verify(restoreFollowersState).call(any());
    }
}
