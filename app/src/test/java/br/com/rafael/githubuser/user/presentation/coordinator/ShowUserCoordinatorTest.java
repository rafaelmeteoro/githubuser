package br.com.rafael.githubuser.user.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.domain.interactor.ShowUser;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ShowUserCoordinatorTest {

    @Mock
    ShowUser showUser;

    @Mock
    GithubUser githubUser;

    ShowUserCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowUserCoordinator(
                        showUser)
        );
    }

    @Test
    public void callCoordinatorShowUser_shouldExecuteInOrder() {
        when(showUser.call(any()))
                .thenReturn(Observable.just(githubUser));

        InOrder callOrder = inOrder(
                showUser);

        Observable.just(githubUser)
                .compose(impl)
                .subscribe();

        callOrder.verify(showUser).call(any());
    }
}
