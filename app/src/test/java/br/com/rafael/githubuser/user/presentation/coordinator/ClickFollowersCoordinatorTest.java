package br.com.rafael.githubuser.user.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.domain.interactor.ClickFollowers;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ClickFollowersCoordinatorTest {

    @Mock
    ClickFollowers clickFollowers;

    ClickFollowersCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ClickFollowersCoordinator(
                        clickFollowers)
        );
    }

    @Test
    public void callCoordinatorClickFollowers_shouldExecuteInOrder() {
        String username = "username";

        when(clickFollowers.call(any()))
                .thenReturn(Observable.just(username));

        InOrder callOrder = inOrder(
                clickFollowers);

        Observable.just(username)
                .compose(impl)
                .subscribe();

        callOrder.verify(clickFollowers).call(any());
    }
}
