package com.example.crosswalkdemo;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by yinzhiyu on 2018-3-28.
 */

public class ScreenshotUtil {
    public static void saveBitmap(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
