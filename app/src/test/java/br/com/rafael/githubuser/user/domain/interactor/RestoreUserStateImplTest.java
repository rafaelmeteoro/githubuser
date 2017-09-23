package br.com.rafael.githubuser.user.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;

public class RestoreUserStateImplTest {

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
                        Schedulers.immediate())
        );
    }

    @Test
    public void restoreState_shouldReturnUser() {
        GithubUser githubUser = new GithubUser();
        state.githubUser = githubUser;

        TestSubscriber<GithubUser> subscriber = new TestSubscriber<>();
        Observable.just(state)
                .compose(impl)
                .subscribe(subscriber);

        subscriber.assertNoErrors();
        GithubUser result = subscriber.getOnNextEvents().get(0);

        assertThat(result, sameInstance(githubUser));
    }
}
