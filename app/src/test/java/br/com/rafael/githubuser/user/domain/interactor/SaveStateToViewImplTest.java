package br.com.rafael.githubuser.user.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SaveStateToViewImplTest {

    @Mock
    UserContract.View view;

    @Mock
    UserContract.State state;

    SaveStateToViewImpl impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        impl = spy(
                new SaveStateToViewImpl(
                        view)
        );
    }

    @Test
    public void onCall_shouldDelegateToView() {
        Observable.just(state)
                .compose(impl)
                .subscribe();

        verify(view).saveState(state);
    }
}
