package ru.yandex.hackaton.wheretolive.server.listener;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public interface OnResponseListener<T> {
    void onOk(final T response);

    void onError(final Throwable throwable);
}
