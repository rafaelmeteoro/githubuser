package br.com.rafael.githubuser.followers.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.followers.domain.interactor.GetFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.ShowFollowers;
import br.com.rafael.githubuser.followers.domain.interactor.ShowLoadingFollowers;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class FollowersCoordinatorTest {

    @Mock
    ShowLoadingFollowers showLoadingFollowers;

    @Mock
    GetFollowers getFollowers;

    @Mock
    ShowFollowers showFollowers;

    @Mock
    FollowersViewModelHolder holder;

    FollowersCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new FollowersCoordinator(
                        showLoadingFollowers,
                        getFollowers,
                        showFollowers)
        );
    }

    @Test
    public void callFollowersCoordinator_shouldExecuteInOrder() {
        String username = "username";

        when(showLoadingFollowers.call(any()))
                .thenReturn(Observable.just(username));
        when(getFollowers.call(any()))
                .thenReturn(Observable.just(holder));
        when(showFollowers.call(any()))
                .thenReturn(Observable.just(holder));

        InOrder callOrder = inOrder(
                showLoadingFollowers,
                getFollowers,
                showFollowers
        );

        Observable.just(username)
                .compose(impl)
                .subscribe();

        callOrder.verify(showLoadingFollowers).call(any());
        callOrder.verify(getFollowers).call(any());
        callOrder.verify(showFollowers).call(any());
    }
}
