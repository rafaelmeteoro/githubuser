package br.com.rafael.githubuser.user.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class RestoreUserStateImplTest {

    @Mock
    UserContract.View view;

    @Mock
    UserContract.State state;

    @Mock
    GithubUser githubUser;

    RestoreUserState impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new RestoreUserStateImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void restoreState_shouldStateContent() {
        GithubUser githubUser = new GithubUser();
        state.isShowingUserLoadError = false;
        state.githubUser = githubUser;

        Observable.just(state)
                .compose(impl)
                .subscribe();

        verify(view).showContentState();
        verify(view).showUser(any());

        verify(view, never()).showEmptySate();
        verify(view, never()).showErrorState();
    }

    @Test
    public void restoreState_shouldStateEmpty() {
        state.isShowingUserLoadError = false;
        state.githubUser = null;

        Observable.just(state)
                .compose(impl)
                .subscribe();

        verify(view).showEmptySate();

        verify(view, never()).showContentState();
        verify(view, never()).showUser(any());
        verify(view, never()).showErrorState();
    }

    @Test
    public void restoreState_shouldStateError() {
        state.isShowingUserLoadError = true;

        Observable.just(state)
                .compose(impl)
                .subscribe();

        verify(view).showErrorState();

        verify(view, never()).showContentState();
        verify(view, never()).showUser(any());
        verify(view, never()).showEmptySate();
    }
}
