package br.com.rafael.githubuser.core.lifecycle;

import android.arch.lifecycle.LifecycleObserver;

import rx.Subscription;

public interface AutomaticUnsubscriber extends LifecycleObserver {
    void add(Subscription subscription);
}
