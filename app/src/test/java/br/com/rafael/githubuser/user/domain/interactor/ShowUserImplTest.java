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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShowUserImplTest {

    @Mock
    UserContract.View view;

    @Mock
    GithubUser githubUser;

    ShowUser impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowUserImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void showUser_shouldContentState() {
        Observable.just(githubUser)
                .compose(impl)
                .subscribe();

        verify(view).showContentState();
        verify(view).showUser(any());
    }

    @Test
    public void showUser_shouldEmptyState() {
        githubUser = null;

        Observable.just(githubUser)
                .compose(impl)
                .subscribe();

        verify(view).showEmptySate();
    }
}
