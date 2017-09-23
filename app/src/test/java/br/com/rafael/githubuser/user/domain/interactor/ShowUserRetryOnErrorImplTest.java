package br.com.rafael.githubuser.user.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.core.client.exception.RetrofitException;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShowUserRetryOnErrorImplTest {

    @Mock
    UserContract.View view;

    ShowUserRetryOnError impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        impl = spy(
                new ShowUserRetryOnErrorImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void onCall_shouldDelegateToView() {
        Observable.error(mock(RetrofitException.class))
                .compose(impl)
                .subscribe();

        verify(view).showUserError();
    }
}
