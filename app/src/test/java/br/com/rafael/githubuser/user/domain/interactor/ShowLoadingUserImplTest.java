package br.com.rafael.githubuser.user.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShowLoadingUserImplTest {

    @Mock
    UserContract.View view;

    ShowLoadingUser impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowLoadingUserImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void onCall_shouldDelgateToView() {
        String username = "username";

        Observable.just(username)
                .compose(impl)
                .subscribe();

        verify(view).showLoadingState();
    }
}
