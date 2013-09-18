package ru.yandex.hackaton.wheretolive.server;

import java.text.MessageFormat;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class ParseResponseException extends Exception {

    private final int mServerCode;

    public ParseResponseException(final String detailMessage, final int serverCode) {
        super(detailMessage);
        mServerCode = serverCode;
    }

    public int getServerCode() {
        return mServerCode;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}, server code: {1}", super.toString(), mServerCode);
    }

}
