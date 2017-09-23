package br.com.rafael.githubuser.user.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.domain.interactor.ShowLoadingUser;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ShowLoadingUserCoordinatorTest {

    @Mock
    ShowLoadingUser showLoadingUser;

    ShowLoadingUserCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowLoadingUserCoordinator(
                        showLoadingUser)
        );
    }

    @Test
    public void callCoordinatorShowLoadingUser_shouldExecuteInOrder() {
        String username = "username";

        when(showLoadingUser.call(any()))
                .thenReturn(Observable.just(username));

        InOrder callOrder = inOrder(
                showLoadingUser);

        Observable.just(username)
                .compose(impl)
                .subscribe();

        callOrder.verify(showLoadingUser).call(any());
    }
}
