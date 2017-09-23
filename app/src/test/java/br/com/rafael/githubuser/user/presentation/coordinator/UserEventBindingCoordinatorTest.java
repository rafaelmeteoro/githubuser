package br.com.rafael.githubuser.user.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserEventBindingCoordinatorTest {

    @Mock
    ClickFollowersCoordinator clickFollowersCoordinator;

    @Mock
    SaveStateCoordinator saveStateCoordinator;

    @Mock
    ShowLoadingUserCoordinator showLoadingUserCoordinator;

    @Mock
    GetUserCoordinator getUserCoordinator;

    @Mock
    ShowUserCoordinator showUserCoordinator;

    @Mock
    AutomaticUnsubscriber automaticUnsubscriber;

    @Mock
    UserContract.View view;

    @Mock
    UserContract.State state;

    @Mock
    GithubUser githubUser;

    UserEventBindingCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new UserEventBindingCoordinator(
                        clickFollowersCoordinator,
                        saveStateCoordinator,
                        showLoadingUserCoordinator,
                        getUserCoordinator,
                        showUserCoordinator,
                        automaticUnsubscriber,
                        view)
        );
    }

    @Test
    public void callCoordinatorUserEventBinding_shouldExecuteInOrder() {
        String username = "username";

        when(view.onFollowersClicked())
                .thenReturn(Observable.just(username));
        when(view.onSaveState())
                .thenReturn(Observable.just(state));
        when(view.onRetryClicked())
                .thenReturn(Observable.just(username));

        when(clickFollowersCoordinator.call(any()))
                .thenReturn(Observable.just(username));
        when(saveStateCoordinator.call(any()))
                .thenReturn(Observable.just(state));
        when(showLoadingUserCoordinator.call(any()))
                .thenReturn(Observable.just(username));
        when(getUserCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));
        when(showUserCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));

        InOrder callOrder = inOrder(
                clickFollowersCoordinator,
                saveStateCoordinator,
                showLoadingUserCoordinator,
                getUserCoordinator,
                showUserCoordinator);

        Observable.just(username)
                .compose(impl)
                .subscribe();

        callOrder.verify(clickFollowersCoordinator).call(any());
        callOrder.verify(saveStateCoordinator).call(any());
        callOrder.verify(showLoadingUserCoordinator).call(any());
        callOrder.verify(getUserCoordinator).call(any());
        callOrder.verify(showUserCoordinator).call(any());

        int expectedSubscribers = 3;
        verify(automaticUnsubscriber, times(expectedSubscribers)).add(any());
    }
}
