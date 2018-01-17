package com.huami.merchant.util;

import android.util.Base64;

import com.huami.merchant.mvpbase.BaseConsts;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Henry.geng on 2017/5/12.
 */

public class EncryptUtil {
    private final static String empty_key ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD/jU7MtLJxDdA+hWv5wxyOHw4I\n" +
            "YhULJMdpj4aoEKPtHVzfZkrdoHPj0JYPzBZmpChb3Eut63pryBq+rZHideSDfI56\n" +
            "ETpxZ6P6euRzDVRPjGS+5HoZf5pB3cmQok4SYLkhYy2f1v6tWpEpA6tOBVKcUD1q\n" +
            "VMm2Bva9sf7zFSJzCwIDAQAB";
    public static String PUBLIC_KEY = SPCache.getString(BaseConsts.SharePreference.PUBLIC_KEY_STORE,empty_key);
    private static final String RSA_ENCRYPT_CODE = "RSA/ECB/PKCS1Padding";
    private static final String AES_ENCRYPT_CODE = "AES/ECB/PKCS5Padding";
    private static final String[] lower_case = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    /**
     * 随机数字字母类型
     * @param randomCount 16位
     * @return
     */
    public static String getRandom(int randomCount) {
        StringBuilder builder = new StringBuilder();//线程非安全的
        List<Integer> diffRandom = getDiffRandom(randomCount, 26);
        for (int index : diffRandom) {
            builder.append(lower_case[index]);
        }
        return builder.toString();
    }
    /**
     * 生成16个不同的随机数，且随机数区间为[0,16)
     * @return
     */
    private static List<Integer> getDiffRandom(int count, int range){
        // 生成 [0-n) 个不重复的随机数
        // list 用来保存这些随机数
        List<Integer> list = new ArrayList();
        Random rand = new Random();
        int num = 0;
        for (int i = 0; i < count; i++) {
            do {
                // 如果产生的数相同继续循环
                num = rand.nextInt(range);
            } while (list.contains(num));
            list.add(num);
        }
        return list;
    }

    /**
     * rsa加密
     * @param random 随机字符串
     * @return
     */
    public static String RSAEncrypt(String random){
        byte[] buffer = Base64.decode(PUBLIC_KEY, Base64.NO_WRAP);
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(RSA_ENCRYPT_CODE);// 此处如果写成"RSA"加密出来的信息JAVA服务器无法解析
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(random.getBytes("utf-8"));
            return Base64.encodeToString(output, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES加密
     * @param random 随机数
     * @param input 明文需要加密的字段
     * @return
     */
    public static String AESEncrypt(String random,String input){
        try {
            byte[] en_byte = random.getBytes("utf-8");
            SecretKeySpec key = new SecretKeySpec(en_byte, "AES");
            Cipher cipher = Cipher.getInstance(AES_ENCRYPT_CODE);// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] results = cipher.doFinal(input.getBytes("utf-8"));
            return Base64.encodeToString(results, Base64.NO_WRAP);
        } catch (Exception e) {

        }
        return null;
    }
    /**
     * AES解密
     * @param random 随机数
     * @param input 密文需要解密的字段
     * @return
     */
    public static String AESDecrypt(String random,String input){
        try {
            byte[] en_byte = random.getBytes("utf-8");
            SecretKeySpec key = new SecretKeySpec(en_byte, "AES");
            Cipher cipher = Cipher.getInstance(AES_ENCRYPT_CODE);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] decode = Base64.decode(input, Base64.DEFAULT);
            byte[] results = cipher.doFinal(decode);
            return new String(results);
        } catch (Exception e) {
        }
        return null;
    }

}
