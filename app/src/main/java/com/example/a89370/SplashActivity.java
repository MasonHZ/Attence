package com.example.a89370;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a89370.util.StreamUtil;
import com.example.a89370.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
public class SplashActivity extends AppCompatActivity {


    //需要升级的状态码
    private static final int UPDATE_VERSION = 100;
    private static final int ENTER_HOME = 101;
    private static final int URL_ERROR = 102;
    private static final int IO_ERROR = 103;
    private static final int JSON_ERROR = 104;


    private TextView splash_tv_version;
    private RelativeLayout splash_rl_root;
    private int mLocalVersionCode;
    private Context mContext;
    private String mVersionDes;
    private String mDownloadUrl;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VERSION:
                    showUpdateDialog();
                    break;
                case ENTER_HOME:
                    //进入应用程序主界面
                    enterHome();
                    break;
                case URL_ERROR:
                    //url_error
                    ToastUtil.show(mContext, "url_error");
                    enterHome();
                    break;
                case IO_ERROR:
                    //IO_ERROR
                    ToastUtil.show(mContext, "IO_ERROR");
                    enterHome();
                    break;
                case JSON_ERROR:
                    //JSON_ERROR
                    ToastUtil.show(mContext, "JSON_ERROR");
                    enterHome();
                    break;

            }
        }
    };



    /**
     * 弹出对话框，提示用户需要更新
     */
    private void showUpdateDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //设置左上角图标
        builder.setIcon(R.mipmap.smile);
        //设置弹出框标题
        builder.setTitle("版本更新");
        //设置主题内容
        builder.setMessage(mVersionDes);

        // 积极按钮，即确定选择按钮
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下载apk，系统json文件提供了apk的下载地址
                downloadApk();
            }
        });

        builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                enterHome();
                dialog.dismiss();
            }
        });

        builder.show();

    }

    /**
     * 借助 XUtils3 工具包帮助下载
     * 需要打开用户存储权限
     * Android6.0以上需要添加权限校验，系统才能默认给应用程序开启权限
     * （实在不想添加权限校验 将target设置成23以下就行了）
     */
    private void downloadApk() {
        //apk下载链接地址，放置apk的所在位置

        //1.判断sd卡是否可用
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            RequestParams params = new RequestParams(mDownloadUrl);

            //2.获取sd卡路径
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "attence.apk";
            Log.e("path====>", path + "");
            //3.发送请求，获取apk
            params.setSaveFilePath(path);
            params.setAutoRename(true);
            x.http().post(params, new Callback.ProgressCallback<File>() {
                @Override
                public void onWaiting() {

                }

                @Override
                public void onStarted() {

                }

                @Override
                public void onLoading(long total, long current, boolean isDownloading) {

                }

                @Override
                public void onSuccess(File result) {
                    Log.e("=====>", "success");
                    installApk(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });

        }
    }

    /**
     * @param result 安装包文件
     *               开启安装界面
     *               <p>
     *               Android7.0以上需要配置 provider
     */
    private void installApk(File result) {
        Log.e("===============>", "install");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
        startActivityForResult(intent, 0);
    }


    /**
     *
     * 开启一个Activity后返回结果调用的方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        enterHome();
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 进入应用程序主界面
     */
    private void enterHome() {

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        finish();//即不在回退到欢迎界面，回退直接退出程序


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = this;

        //初始化UI
        initUI();
        //初始化数据
        initData();
        //初始化动画
        initAnimation();



    }

    /**
     * 初始化动画方法
     */
    private void initAnimation() {
        //淡入淡出动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        //设置动画时长
        alphaAnimation.setDuration(3000);
        splash_rl_root.startAnimation(alphaAnimation);


    }

    /**
     * 获取数据方法
     */
    private void initData() {
        //1.应用版本名称
        splash_tv_version.setText("版本名称:" + getVersionName());
        // 检测（本地版本号跟服务器版本号比对）是否有更新，有过有更新，提示下载(m代表用户member)
        // 2.获取本地版本号
        mLocalVersionCode = getVersionCode();
        // 3.获取服务器版本号（客户端发请求，服务端给响应，（json，xml））
        // http://www.oxxx.com/update.json?key=value  返回200请求成功，流的方式将数据读取下来
        //
        // json中要包含的信息
        // 更新版本的版本名称
        // 新版本的描述信息
        // 服务端版本号
        // 新版本apk下载地址
        checkVersion();

    }

    /**
     * 确认手机版本号与服务器版本号是否一致
     */
    private void checkVersion() {

        new Thread() {

            @Override
            public void run() {

                //Message msg = new Message();  两种方式，这种方式效率较低
                Message msg = Message.obtain();//这种效率高
                Long start = System.currentTimeMillis();
                try {

                    URL url = new URL("http://192.168.1.103:8080/update.json");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    //conn.setRequestMethod("GET");

                    conn.setConnectTimeout(2000);

                    conn.setReadTimeout(2000);
                    Log.e("conn====>", "" + conn);

                    Log.e("======>", "" + conn.getResponseCode());
                    if (conn.getResponseCode() == 200) {

                        InputStream is = conn.getInputStream();

                        String str = StreamUtil.stream2String(is);

                        Log.e("======>", str + "");

                        //获取json对象全部内容   jsonObject相当于{  }中的内容
                        JSONObject jsonObject = new JSONObject(str);
                        // 逐步解析json
                        String versionName = jsonObject.getString("versionName");
                        mVersionDes = jsonObject.getString("versionDes");
                        String version = jsonObject.getString("version");
                        mDownloadUrl = jsonObject.getString("downloadUrl");


                        if (mLocalVersionCode < Integer.parseInt(version)) {
                            // 提示用户需要更新,弹出对话框，即操纵UI (需要用到消息机制)
                            msg.what = UPDATE_VERSION;

                        } else {
                            // 进入主界面
                            msg.what = ENTER_HOME;

                        }
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    msg.what = URL_ERROR;
                } catch (IOException e) {
                    e.printStackTrace();
                    msg.what = IO_ERROR;
                } catch (JSONException e) {
                    e.printStackTrace();
                    msg.what = JSON_ERROR;
                } finally {
                    //指定睡眠时间,防止应用程序反应过快，直接刷掉了第一个splash界面
                    Long end = System.currentTimeMillis();
                    if (end - start < 3500) {
                        try {
                            Thread.sleep(3500 - (end - start));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }.start();


    }

    /**
     * 获取版本号：清单文件中
     *
     * @return 应用程序版本号  返回0异常
     */
    private int getVersionCode() {

        //1.获取包管理者
        PackageManager pm = getPackageManager();
        //2.从包管理者对象中，获取指定包的基本信息（版本名，版本编号）,传0代表基本信息
        try {
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
            //3.获取版本号
            return info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }


    /**
     * 获取版本名称：清单文件中
     *
     * @return 应用程序名称     返回null带包异常
     */
    private String getVersionName() {

        //1.获取包管理者
        PackageManager pm = getPackageManager();
        //2.从包管理者对象中，获取指定包的基本信息（版本名，版本编号）,传0代表基本信息
        try {
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
            //3.获取版本名称
            return info.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * 初始化UI方法
     */
    private void initUI() {
        splash_tv_version = (TextView) findViewById(R.id.splash_tv_version);
        splash_rl_root = (RelativeLayout) findViewById(R.id.splash_rl_root);
    }
}
