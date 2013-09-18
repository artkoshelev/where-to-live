package ru.yandex.hackaton.wheretolive.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class DeviceUtil {
    private static Context context;
    private static ProgressDialog dialog;
    private static String message;
    private static int duration;
    private static final Handler actionHandler = new Handler();

    private static AtomicInteger countStartDialog = new AtomicInteger(0);
    private static AtomicInteger countConnectionError = new AtomicInteger(0);

    public static void init(Context context) {
        DeviceUtil.context = context;
    }


    public static void showProgressDialog(Context context, final String title, final String msg) {
        countStartDialog.incrementAndGet();
        if (context != null && (dialog == null || !dialog.isShowing())) {
            dialog = new ProgressDialog(context);
            dialog.setTitle(title);
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.setMessage(msg);
            dialog.show();
        }
    }

    public static void hideProgressDialog() {
        if (countStartDialog.get() != 0) {
            countStartDialog.decrementAndGet();
        }
        if (dialog != null && dialog.isShowing() && countStartDialog.get() == 0) {
            dialog.dismiss();
        }
    }

    public static void showNotification(final String msg, int messageDuration) {
        message = msg;
        duration = messageDuration;
        boolean isEqualMsg = msg.equals("Необходимо подключение к интернету!");
        if (!isEqualMsg) {
            actionHandler.post(mShowNotification);
        } else if (countConnectionError.get() == 0) {
            actionHandler.post(mShowNotification);
            countConnectionError.incrementAndGet();
        } else {
            countConnectionError.incrementAndGet();
        }

        if (isEqualMsg && countConnectionError.get() == 3) {
            countConnectionError.set(0);
        }
    }

    static final Runnable mShowNotification = new Runnable() {
        public void run() {
            Toast.makeText(context, message, duration).show();
        }
    };

}
