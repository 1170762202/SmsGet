package com.example.smsget;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Author: Zlx on 2019/8/3.
 * Email:1170762202@qq.com
 */
public class FileUtil {

    private static String TAG = "TAG";
    private static String folderName = "ZZZ";
    private static String fileName = "user.txt";

    public static void writeTxt(String content, Context context) {
        //新建文件夹
        File sdCardDir = new File(Environment.getExternalStorageDirectory(), folderName);
        Log.e("TAG", "write:" + sdCardDir.getParent());
        if (!sdCardDir.exists()) {
            if (!sdCardDir.mkdirs()) {
                try {
                    sdCardDir.createNewFile();
                } catch (Exception e) {
                    Log.e("TAG", e.toString());

                }
            }
        }


        try {
            //新建文件
            File saveFile = new File(sdCardDir, fileName);

            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            // FileOutputStream outStream =null;
            //outStream = new FileOutputStream(saveFile);

            final FileOutputStream outStream = new FileOutputStream(saveFile);

            try {
                outStream.write(content.getBytes());
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("TAG", e.toString());
            }
            Log.e("TAG", "写入成功");
            Toast.makeText(context, "写入成功", Toast.LENGTH_SHORT).show();
            //outStream.write("测试写入文件".getBytes());
            //outStream.close();
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    public static String readTxt() {

        BufferedReader bre = null;
        String str = null;
        try {
            String file = Environment.getExternalStorageDirectory() + "/" + folderName + "/" + fileName + "";
            bre = new BufferedReader(new FileReader(file));//此时获取到的bre就是整个文件的缓存流
            while ((str = bre.readLine()) != null) { // 判断最后一行不存在，为空结束循环

                Log.e(TAG, "readTxt: a------------" + str);
                String[] arr = str.split("\\s+");

                Log.e("TAG", "sms:" + str);
            }
            return str;
        } catch (Exception e) {
            Log.e(TAG, "readTxt: ---------------" + e.toString());

        }

        return null;
    }
}
