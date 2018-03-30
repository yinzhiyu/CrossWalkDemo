package com.example.crosswalkdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.xwalk.core.XWalkActivity;

import java.io.FileOutputStream;

import static android.content.ContentValues.TAG;

/**
 * @author sanji
 *         显示Crosswalk webview的界面
 */
public class CrossWebviewActivity extends XWalkActivity {

    private CrosswalkService crosswalkService;
    private ImageView iv_screenshot;
    private static int CODE_FOR_WRITE_PERMISSION = 110;
    private LinearLayout webview_lay;

    public static void start(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, CrossWebviewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使屏幕保持常亮
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            WebView.enableSlowWholeDocumentDraw();
//        }
        setContentView(R.layout.activity_cross_webview);

        initView();
        initData();
    }

    private void initView() {
        webview_lay = findViewById(R.id.webview_lay);
        iv_screenshot = findViewById(R.id.iv_screenshot);
    }

    private void initData() {
        crosswalkService = new CrosswalkService();
    }

    public void onButtonClick(View view) {
        Toast.makeText(this, "开始截屏", Toast.LENGTH_SHORT).show();
//        Bitmap bitmap = crosswalkService.captureWebView();
//        ScreenshotUtil.saveBitmap(bitmap);
//使用兼容库就无需判断系统版本
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
//            go();
            Bitmap bitmap = crosswalkService.captureWebView();
            ScreenshotUtil.saveBitmap(bitmap);
        }
//需要弹出dialog让用户手动赋予权限
        else {
            ActivityCompat.requestPermissions(CrossWebviewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_FOR_WRITE_PERMISSION);

        }
//        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
//
//            Activity activty=this;
//
//            ActivityCompat.requestPermissions(activty,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    CODE_FOR_WRITE_PERMISSION);
//            return;
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ToastUtil.showMsgShort("crosswalk webview");
        //if (null != crosswalkService) crosswalkService.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //if (null != crosswalkService) crosswalkService.onPause();
    }

    /**
     * 加载网页和配置在此方法中进行
     * 说明：XWalkview的配置和webview基本相同，可以参考webview对XWalkView进行配置
     */
    @Override
    protected void onXWalkReady() {
        if (null != crosswalkService) {
            crosswalkService.showWebview(webview_lay);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (null != crosswalkService) crosswalkService.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        if (null != crosswalkService) crosswalkService.onDestroy();
        super.onDestroy();
    }

    //截图下来,然后保存
    public void go() {
        Bitmap bitmap = Bitmap.createBitmap(webview_lay.getWidth(), webview_lay.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        webview_lay.draw(canvas);
//保存 saveFiletry
        try {

            //获取内部存储状态
            String state = Environment.getExternalStorageState();
//如果状态不是mounted，无法读写
            if (!state.equals(Environment.MEDIA_MOUNTED)) {
                return;
            }
            String fileName = Environment.getExternalStorageDirectory().getPath() + "/webview_capture4.png";
//            String fileName = state + "/webview_capture4.jpg";
            FileOutputStream fos = new FileOutputStream(fileName);
//压缩bitmap到输出流中
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
            fos.close();
            Toast.makeText(CrossWebviewActivity.this, "截屏成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_FOR_WRITE_PERMISSION) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意使用write
//                go();
                Bitmap bitmap = crosswalkService.captureWebView();
                ScreenshotUtil.saveBitmap(bitmap);
            } else {
                //用户不同意，自行处理即可
                finish();
            }
        }
    }
}
