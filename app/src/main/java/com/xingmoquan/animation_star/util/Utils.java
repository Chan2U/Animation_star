package com.xingmoquan.animation_star.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by qq on 16/7/19.
 */
public class Utils {

    /**
     * Convert dp to px value
     *
     * @param ctx
     * @param dpValue
     * @return
     */
    public static int dpToPx(Context ctx, int dpValue) {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return (int) ((dpValue * dm.density) + 0.5f);
    }

    public static  int getDefaultHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getHeight();
    }

}
