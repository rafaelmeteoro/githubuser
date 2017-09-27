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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShowFollowersImplTest {

    @Mock
    FollowersContract.View view;

    ShowFollowers impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowFollowersImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void showFollowers_shouldContentState() {
        FollowersViewModelHolder holder = createHolder();

        Observable.just(holder)
                .compose(impl)
                .subscribe();

        verify(view).showContentState();
        verify(view).showFollowers(any());
    }

    @Test
    public void showFollowers_shouldEmptyState() {
        FollowersViewModelHolder holder = null;

        Observable.just(holder)
                .compose(impl)
                .subscribe();

        verify(view).showEmptyState();
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
