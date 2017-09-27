package br.com.rafael.githubuser.user.presentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.presentation.coordinator.ClickFollowersCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.RestoreStateCoordinator;
import br.com.rafael.githubuser.user.presentation.coordinator.UserCoordinator;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserPresenterTest {

    @Mock
    UserCoordinator userCoordinator;

    @Mock
    RestoreStateCoordinator restoreStateCoordinator;

    @Mock
    ClickFollowersCoordinator clickFollowersCoordinator;

    @Mock
    AutomaticUnsubscriber automaticUnsubscriber;

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
                        userCoordinator,
                        restoreStateCoordinator,
                        clickFollowersCoordinator,
                        automaticUnsubscriber)
        );
    }

    @Test
    public void initializePresenter_shouldCAllCoordinatorsInOrder() {
        when(userCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));

        InOrder callOrder = inOrder(
                userCoordinator
        );

        presenter.initialize(any());

        callOrder.verify(userCoordinator).call(any());
    }

    @Test
    public void initializePresenter_shouldAddSubscriptionsToAutomaticUnsubscriber() {
        when(userCoordinator.call(any()))
                .thenReturn(Observable.just(githubUser));

        presenter.initialize(any());

        verify(automaticUnsubscriber).add(any());
    }

    @Test
    public void initializeFromStatePresenter_shouldCallCoordinatorsInOrder() {
        when(restoreStateCoordinator.call(any()))
                .thenReturn(Observable.just(state));

        InOrder callOrder = inOrder(
                restoreStateCoordinator
        );

        presenter.initializeFromState(any());

        callOrder.verify(restoreStateCoordinator).call(any());
    }

    @Test
    public void initializeFromStatePresenter_shouldAddSubscriptionsToAutomaticUnsubscriber() {
        when(restoreStateCoordinator.call(any()))
                .thenReturn(Observable.just(state));

        presenter.initializeFromState(any());

        verify(automaticUnsubscriber).add(any());
    }

    @Test
    public void clickFollowers_shouldCAllCoordinatorsInOrder() {
        String username = "username";

        when(clickFollowersCoordinator.call(any()))
                .thenReturn(Observable.just(username));

        InOrder callOrder = inOrder(
                clickFollowersCoordinator
        );

        presenter.clickFollowers(username);

        callOrder.verify(clickFollowersCoordinator).call(any());
    }

    @Test
    public void clickFollowers_shouldAddSubscriptionsToAutomaticUnsubscriber() {
        String username = "username";

        when(clickFollowersCoordinator.call(any()))
                .thenReturn(Observable.just(username));

        presenter.clickFollowers(username);

        verify(automaticUnsubscriber).add(any());
    }
}
