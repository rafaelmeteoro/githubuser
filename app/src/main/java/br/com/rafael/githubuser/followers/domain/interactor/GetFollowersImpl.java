package br.com.rafael.githubuser.followers.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.di.qualifers.IOScheduler;
import br.com.rafael.githubuser.core.di.qualifers.UIScheduler;
import br.com.rafael.githubuser.followers.data.models.Follower;
import br.com.rafael.githubuser.followers.data.repository.GithubFollowerRepository;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;
import rx.Scheduler;

public class GetFollowersImpl implements GetFollowers {

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;
    private GithubFollowerRepository githubFollowerRepository;

    @Inject
    public GetFollowersImpl(@IOScheduler Scheduler ioScheduler,
                            @UIScheduler Scheduler uiScheduler,
                            GithubFollowerRepository githubFollowerRepository) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.githubFollowerRepository = githubFollowerRepository;
    }

    @Override
    public Observable<FollowersViewModelHolder> getFollowers(String username) {
        return githubFollowerRepository.getFollowers(username)
                .flatMap(this::mapViewModelImage)
                .toList()
                .map(this::mapper)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler);
    }

    private Observable<Follower> mapViewModelImage(List<Follower> followers) {
        return Observable.from(followers)
                .map(this::mapper);
    }

    private Follower mapper(Follower follower) {
        follower.isLeft((follower.id() % 2) == 0);
        return follower;
    }

    private FollowersViewModelHolder mapper(List<Follower> followers) {
        return new FollowersViewModelHolder(followers);
    }
}
