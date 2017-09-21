package br.com.rafael.githubuser.followers.presentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.core.client.exception.RetrofitException;
import br.com.rafael.githubuser.core.lifecycle.AutomaticUnsubscriber;
import br.com.rafael.githubuser.followers.domain.interactor.GetFollowers;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FollowersPresenterTest {

    @Mock
    GetFollowers getFollowers;

    @Mock
    FollowersContract.View view;

    @Mock
    AutomaticUnsubscriber automaticUnsubscriber;

    FollowersContract.Presenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = spy(
                new FollowersPresenter(
                        getFollowers,
                        view,
                        automaticUnsubscriber)
        );
    }

    @Test
    public void initializePresenter_shouldDisplayFollowers() {
        when(getFollowers.getFollowers(any()))
                .thenReturn(Observable.just(mock(FollowersViewModelHolder.class)));

        presenter.initialize(any());

        verify(view).showFollowersLoading();
        verify(view).showFollowers(any());
    }

    @Test
    public void initializePresenter_shouldDisplayError() {
        when(getFollowers.getFollowers(any()))
                .thenReturn(Observable.error(mock(RetrofitException.class)));

        presenter.initialize(any());

        verify(view).showFollowersLoading();
        verify(view).showFollowersError();
    }

    @Test
    public void initializeFromStatePresenter_shouldDisplayFollowers() {

        presenter.initializeFromState(any());

        verify(view).showFollowers(any());
    }
}
