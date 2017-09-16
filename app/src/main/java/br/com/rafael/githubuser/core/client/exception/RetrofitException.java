package br.com.rafael.githubuser.core.client.exception;

import com.google.gson.Gson;

import java.io.IOException;

import lombok.Getter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Wraps a response body into an exception.
 */
public class RetrofitException extends RuntimeException {

    private static final Gson GSON = new Gson();

    private final String url;
    private final ResponseWrapper response;
    private final Kind kind;
    private final Retrofit retrofit;

    RetrofitException(String message, String url, ResponseWrapper response,
                      Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }

    public static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();

        ResponseWrapper wrapper = null;
        try {
            wrapper = new ResponseWrapper(response.code(),
                    response.errorBody().string());
        } catch (IOException ignored) {
        }

        return new RetrofitException(message, url, wrapper, Kind.HTTP, null, retrofit);
    }

    public static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.NETWORK, exception, null);
    }

    public static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc
     */
    public ResponseWrapper getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    public Kind getKind() {
        return kind;
    }

    /**
     * The Retrofit this request was executed on
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * Converts the error body to a given class.
     *
     * @param cls Class to be converted.
     * @return
     */
    public <T> T getErrorBodyAs(Class<T> cls) {
        return GSON.fromJson(response.errorBody, cls);
    }

    public static class ResponseWrapper {
        @Getter
        private int code;

        @Getter
        private String errorBody;

        public ResponseWrapper(int code, String errorBody) {
            this.code = code;
            this.errorBody = errorBody;
        }
    }

    /**
     * Identifies the event kind which triggered a {@link RetrofitException}
     */
    public enum Kind {
        /**
         * An {@link java.io.IOException} ocurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error ocurred while attempting to execute a request. It is best pratice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}
