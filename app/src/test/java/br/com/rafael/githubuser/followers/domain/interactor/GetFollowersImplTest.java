package br.com.rafael.githubuser.followers.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.rafael.githubuser.followers.data.models.Follower;
import br.com.rafael.githubuser.followers.data.repository.GithubFollowerRepository;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class GetFollowersImplTest {

    @Mock
    GithubFollowerRepository githubFollowerRepository;

    GetFollowers impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new GetFollowersImpl(
                        Schedulers.immediate(),
                        Schedulers.immediate(),
                        githubFollowerRepository)
        );
    }

    @Test
    public void onCall_shouldReturnHolder() {
        String username = "username";

        int expectedSize = 10;
        List<Follower> expectedList = createFollowers(expectedSize);

        when(githubFollowerRepository.getFollowers(any()))
                .thenReturn(Observable.just(expectedList));

        TestSubscriber<FollowersViewModelHolder> subscriber = new TestSubscriber<>();

        Observable.just(username)
                .compose(impl)
                .subscribe(subscriber);

        subscriber.assertNoErrors();

        FollowersViewModelHolder holder = subscriber.getOnNextEvents().get(0);

        assertThat(holder.getViewModels().size(), is(expectedSize));
    }

    private List<Follower> createFollowers(int size) {
        List<Follower> followers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            followers.add(new Follower()
                    .id(i));
        }

        return followers;
    }
}
