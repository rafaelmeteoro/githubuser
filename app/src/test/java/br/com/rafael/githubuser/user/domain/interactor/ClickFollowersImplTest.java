package br.com.rafael.githubuser.user.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ClickFollowersImplTest {

    @Mock
    UserContract.View view;

    ClickFollowers impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ClickFollowersImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void onCall_shouldDelegateToView() {
        String username = "username";

        Observable.just(username)
                .compose(impl)
                .subscribe();

        verify(view).launchFollowersActivity(any());
    }
}
