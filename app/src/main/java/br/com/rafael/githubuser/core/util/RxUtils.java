package br.com.rafael.githubuser.core.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Contains rx-related auxiliary methods.
 */
public class RxUtils {

    private static final Observable.Transformer SCHEDULERS_TRANSFORMER;

    static {
        SCHEDULERS_TRANSFORMER = observable ->
                ((Observable) observable)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable.Transformer<T, T> schedulersTransform() {
        return SCHEDULERS_TRANSFORMER;
    }

    public static <T extends Throwable, R> Observable.Transformer<R, R>
    retryTransform(Func2<T, Func0<Observable<R>>, Observable<R>> errorHandling,
                   Func0<Observable<R>> retryObservable) {

        return transformer ->
                transformer.onErrorResumeNext(error -> errorHandling.call((T) error,
                        retryObservable));
    }
}
