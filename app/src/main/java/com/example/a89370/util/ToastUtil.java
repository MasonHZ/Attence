package com.example.a89370.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 89370 on 2018/4/23.
 */

public class ToastUtil {

    /**
     * @param context 上下文环境
     * @param str  文本内容
     *     帮助弹出提示
     */
    public static void show(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }

}
