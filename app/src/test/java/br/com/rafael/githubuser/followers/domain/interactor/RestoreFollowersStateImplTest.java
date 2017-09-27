package br.com.rafael.githubuser.followers.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import br.com.rafael.githubuser.followers.data.models.Follower;
import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class RestoreFollowersStateImplTest {

    @Mock
    FollowersContract.View view;

    @Mock
    FollowersContract.State state;

    @Mock
    FollowersViewModelHolder holder;

    RestoreFollowersState impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new RestoreFollowersStateImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void restoreState_shouldStateContent() {
        state.isShowingFollowersLoadError = false;
        state.holder = createHolder();

        Observable.just(state)
                .compose(impl)
                .subscribe();

        verify(view).showContentState();
        verify(view).showFollowers(any());

        verify(view, never()).showEmptyState();
        verify(view, never()).showErrorState();
    }

    @Test
    public void restoreState_shouldStateEmpty() {
        state.isShowingFollowersLoadError = false;
        state.holder = null;

        Observable.just(state)
                .compose(impl)
                .subscribe();

        verify(view).showEmptyState();

        verify(view, never()).showContentState();
        verify(view, never()).showFollowers(any());
        verify(view, never()).showErrorState();
    }

    @Test
    public void restoreState_shouldStateError() {
        state.isShowingFollowersLoadError = true;

        Observable.just(state)
                .compose(impl)
                .subscribe();

        verify(view).showErrorState();

        verify(view, never()).showContentState();
        verify(view, never()).showFollowers(any());
        verify(view, never()).showEmptyState();
    }

    private FollowersViewModelHolder createHolder() {
        List<Follower> followers = Arrays.asList(
                new Follower(),
                new Follower(),
                new Follower()
        );
        return new FollowersViewModelHolder(followers);
    }
}
