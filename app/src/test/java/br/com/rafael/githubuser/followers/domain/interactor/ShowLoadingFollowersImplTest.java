package br.com.rafael.githubuser.followers.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.followers.presentation.FollowersContract;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShowLoadingFollowersImplTest {

    @Mock
    FollowersContract.View view;

    ShowLoadingFollowers impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowLoadingFollowersImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void onCall_ShouldDelegateToView() {
        String username = "username";

        Observable.just(username)
                .compose(impl)
                .subscribe();

        verify(view).showLoadingState();
    }
}
