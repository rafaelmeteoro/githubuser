package br.com.rafael.githubuser.core.client;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import br.com.rafael.githubuser.core.client.exception.RetrofitException;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Mimics the behavior of Retrofit 1 by wrapping network errors into
 * Exception, turing API error handling consistent with rx philosophy.
 */
public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private final RxJavaCallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJavaCallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return null;
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Observable<?>> {

        private final Retrofit retrofit;
        private final CallAdapter<?> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public <R> Observable<?> adapt(Call<R> call) {
            // Even though x is of type Throwable, we must explicitly cast it to Throwable,
            // otherwise the code won't compile.
            return ((Observable) wrapped.adapt(call))
                    .onErrorResumeNext(x -> Observable.error(asRetrofitException((Throwable) x)));
        }

        private RetrofitException asRetrofitException(Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.httpError(response.raw().request().url().toString(),
                        response, retrofit);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknow error
            return RetrofitException.unexpectedError(throwable);
        }
    }
}
