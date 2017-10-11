package com.util;

import java.security.MessageDigest;
/**
 * Created by lvjiawei on 2017/5/21.
 */
public class MD5Util{
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","a",
    "b","c","d","e","f"};

    //把inputString加密
    public static String encode(String inStr){
        return convertMD5(inStr);
    }

    //MD5加密
    private static String convertMD5(String inStr){
        if(inStr != null){
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(inStr.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            }catch (Exception e){
                e.printStackTrace();
            }
        }return null;
    }

    //转换字节数组为十六进制字符串
    private static String byteArrayToHexString(byte[] b){
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < b.length;i++){
            sb.append(byteToHexString(b[i]));
        }
        return sb.toString();
    }

    //转换字节为十六进制字符串
    private static String byteToHexString(byte b){
        int n = b;
        if(n < 0)
            n += 256;
        int d1 = n/16;
        int d2 = n%16;
        return hexDigits[d1] + hexDigits[d2];
    }


}
