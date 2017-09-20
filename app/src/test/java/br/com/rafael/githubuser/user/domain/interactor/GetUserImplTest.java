package br.com.rafael.githubuser.user.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.data.repository.GithubUserRepository;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class GetUserImplTest {

    GetUser useCase;

    @Mock
    GithubUserRepository gitHubApi;

    @Mock
    GithubUser githubUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetUserImpl(
                Schedulers.immediate(),
                Schedulers.immediate(),
                gitHubApi);
    }

    @Test
    public void getUser() throws Exception {
        when(gitHubApi.getUser(any()))
                .thenReturn(Observable.just(githubUser));

        TestSubscriber<GithubUser> subscriber = new TestSubscriber<>();
        useCase.getUser(any()).subscribe(subscriber);

        subscriber.assertNoErrors();

        assertThat(subscriber.getOnNextEvents().get(0), is(githubUser));
    }
}
