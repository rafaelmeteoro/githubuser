package br.com.rafael.githubuser.followers.presentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.followers.presentation.coordinator.FollowersCoordinator;
import br.com.rafael.githubuser.followers.presentation.coordinator.FollowersRestoreStateCoordinator;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FollowersPresenterTest {

    @Mock
    FollowersCoordinator followersCoordinator;

    @Mock
    FollowersRestoreStateCoordinator followersRestoreStateCoordinator;

    @Mock
    AutomaticUnsubscriber automaticUnsubscriber;

    @Mock
    FollowersViewModelHolder holder;

    @Mock
    FollowersContract.State state;

    FollowersContract.Presenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = spy(
                new FollowersPresenter(
                        followersCoordinator,
                        followersRestoreStateCoordinator,
                        automaticUnsubscriber)
        );
    }

    @Test
    public void initializePresenter_shouldCallCoordinatorsInOrder() {
        when(followersCoordinator.call(any()))
                .thenReturn(Observable.just(holder));

        InOrder callOrder = inOrder(
                followersCoordinator
        );

        presenter.initialize(any());

        callOrder.verify(followersCoordinator).call(any());
    }

    @Test
    public void initializePresenter_shouldAddSubscriptionsToAutomaticUnsubscriber() {
        when(followersCoordinator.call(any()))
                .thenReturn(Observable.just(holder));

        presenter.initialize(any());

        verify(automaticUnsubscriber).add(any());
    }

    @Test
    public void initializeFromStatePresenter_shouldCAllCoordinatorsInOrder() {
        when(followersRestoreStateCoordinator.call(any()))
                .thenReturn(Observable.just(state));

        InOrder callOrder = inOrder(
                followersRestoreStateCoordinator
        );

        presenter.initializeFromState(any());

        callOrder.verify(followersRestoreStateCoordinator).call(any());
    }

    @Test
    public void initializeFromStatePresenter_shouldAddSubscriptionsToAutomaticUnsubscriber() {
        when(followersRestoreStateCoordinator.call(any()))
                .thenReturn(Observable.just(state));

        presenter.initializeFromState(any());

        verify(automaticUnsubscriber).add(any());
    }
}
