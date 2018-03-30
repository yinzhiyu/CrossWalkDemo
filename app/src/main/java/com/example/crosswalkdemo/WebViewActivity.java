package com.example.crosswalkdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.just.agentweb.AgentWeb;

import java.io.File;
import java.io.FileOutputStream;

public class WebViewActivity extends AppCompatActivity {
    private LinearLayout mLinearLayout;
    private ImageView iv_all;
    private AgentWeb mAgentWeb;
    private float scale;
    private static int CODE_FOR_WRITE_PERMISSION = 120;
    private static int CODE_FOR_WRITE_YIN = 110;
    public static final int REQUEST_MEDIA_PROJECTION = 0x2893;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
        setContentView(R.layout.activity_web_view);
        mLinearLayout = findViewById(R.id.ll);
        iv_all = findViewById(R.id.iv_all);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("http://www.baidu.com");
        iv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("我是图片");
            }
        });
    }

    public static void start(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, WebViewActivity.class);
        activity.startActivity(intent);
    }

    public void onButtonClick(View view) {
        Bitmap bitmap = getViewBp(view);
        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                filePath = sdCardPath + File.separator + System.currentTimeMillis() + ".png";
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                iv_all.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(filePath)
                        .fitCenter()
                        .into(iv_all);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void onLeftClick(View view) {
        //使用兼容库就无需判断系统版本
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
            screenshot();
        }
//需要弹出dialog让用户手动赋予权限
        else {
            ActivityCompat.requestPermissions(WebViewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_FOR_WRITE_YIN);

        }

    }

    private void screenshot() {
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                filePath = sdCardPath + File.separator + System.currentTimeMillis() + ".png";
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
//                RequestOptions options = new RequestOptions()
//                        .centerCrop()
//                        .placeholder(R.drawable.bayes)
//                        .error(R.drawable.bayes)
//                        .priority(Priority.HIGH);
//                Glide.with(this).load(filePath).apply(options).into(iv_all);
                Glide.with(this)
                        .load(filePath)
                        .fitCenter()
                        .into(iv_all);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap getViewBp(View v) {
        if (null == v) {
            return null;
        }
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        if (Build.VERSION.SDK_INT >= 11) {
            v.measure(View.MeasureSpec.makeMeasureSpec(v.getWidth(),
                    View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(
                    v.getHeight(), View.MeasureSpec.EXACTLY));
            v.layout((int) v.getX(), (int) v.getY(),
                    (int) v.getX() + v.getMeasuredWidth(),
                    (int) v.getY() + v.getMeasuredHeight());
        } else {
            v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        }
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache(), 0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        v.setDrawingCacheEnabled(false);
        v.destroyDrawingCache();
        return b;
    }

    private void toast(String str) {
        Toast.makeText(WebViewActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_FOR_WRITE_PERMISSION) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意使用write
                toast("用户同意使用write");
            } else {
                //用户不同意，自行处理即可
                finish();
            }
        } else if (requestCode == CODE_FOR_WRITE_YIN) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                screenshot();
            } else {
                //用户不同意，自行处理即可
                finish();
            }
        }
    }

}
