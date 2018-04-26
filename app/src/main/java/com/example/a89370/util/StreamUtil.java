package com.example.a89370.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 89370 on 2018/4/23.
 */

public class StreamUtil {


    /**
     * @param is 流对象
     * @return 流转换为字符串    返回null代表异常
     */
    public static String stream2String(InputStream is) {


        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int len = 0;
        byte[] b = new byte[1024];

        try {
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }
}
