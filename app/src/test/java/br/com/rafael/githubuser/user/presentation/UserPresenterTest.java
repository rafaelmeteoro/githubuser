package br.com.rafael.githubuser.user.presentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.presentation.coordinator.GetUserCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.RestoreStateCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.ShowLoadingUserCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.ShowUserCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.UserEventBindingCoordinator;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserPresenterTest {

    @Mock
    ShowLoadingUserCoordinator showLoadingUserCoordinator;

    @Mock
    GetUserCoordinator getUserCoordinator;

    @Mock
    ShowUserCoordinator showUserCoordinator;

    @Mock
    RestoreStateCoordinator restoreStateCoordinator;

    @Mock
    AutomaticUnsubscriber automaticUnsubscriber;

    @Mock
    UserEventBindingCoordinator<String> userEventBindingCoordinator;

    @Mock
    GithubUser githubUser;

    @Mock
    UserContract.State state;

    UserContract.Presenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = spy(
                new UserPresenter(
                        showLoadingUserCoordinator,
                        getUserCoordinator,
                        showUserCoordinator,
                        restoreStateCoordinator,
                        automaticUnsubscriber,
                        userEventBindingCoordinator)
        );
    }

    @Test
    public void initializePresenter_shouldCAllCoordinatorsInOrder() {
        String username = "username";

        when(userEventBindingCoordinator.call(any()))
                .thenReturn(Observable.just(username));
        when(showLoadingUserCoordinator.call(any()))
                .thenReturn(Observable.just(username));
        when(getUserCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));
        when(showUserCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));

        InOrder callOrder = inOrder(
                userEventBindingCoordinator,
                showLoadingUserCoordinator,
                getUserCoordinator,
                showUserCoordinator);

        presenter.initialize(any());

        callOrder.verify(userEventBindingCoordinator).call(any());
        callOrder.verify(showLoadingUserCoordinator).call(any());
        callOrder.verify(getUserCoordinator).call(any());
        callOrder.verify(showUserCoordinator).call(any());
    }

    @Test
    public void initializePresenter_shouldAddSubscriptionsToAutomaticUnsubscriber() {
        String username ="username";

        when(userEventBindingCoordinator.call(any()))
                .thenReturn(Observable.just(username));
        when(showLoadingUserCoordinator.call(any()))
                .thenReturn(Observable.just(username));
        when(getUserCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));
        when(showUserCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));

        presenter.initialize(any());

        verify(automaticUnsubscriber).add(any());
    }

    @Test
    public void initializeFromStatePresenter_shouldRestoreStates() {
        when(restoreStateCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));

        presenter.initializeFromState(state);

        verify(restoreStateCoordinator).call(any());
    }

    @Test
    public void initializeFromStatePresenter_shouldAddSubscriptionsToAutomaticUnsubscriber() {
        when(restoreStateCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));

        presenter.initializeFromState(state);

        verify(automaticUnsubscriber).add(any());
    }
}
