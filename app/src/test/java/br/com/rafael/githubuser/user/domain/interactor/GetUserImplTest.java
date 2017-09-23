package br.com.rafael.githubuser.user.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.data.repository.GithubUserRepository;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUserImplTest {

    @Mock
    GithubUserRepository githubUserRepository;

    @Mock
    GithubUser githubUser;

    GetUser impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new GetUserImpl(
                        Schedulers.immediate(),
                        Schedulers.immediate(),
                        githubUserRepository)
        );
    }

    @Test
    public void getUser() {
        String username = "username";

        when(githubUserRepository.getUser(any()))
                .thenReturn(Observable.just(githubUser));

        Observable.just(username)
                .compose(impl)
                .subscribe();

        verify(githubUserRepository).getUser(username);
    }
}
