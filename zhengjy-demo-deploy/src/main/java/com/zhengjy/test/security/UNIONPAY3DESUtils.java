package com.zhengjy.test.security;

/**
 * Created by Jiyang.Zheng on 2019/2/19 15:47.
 */

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class UNIONPAY3DESUtils {
    private static Logger logger = LoggerFactory.getLogger(UNIONPAY3DESUtils.class);

    /**
     * 3DES Cbc模式 加密
     * @param key 密钥
     * @param data 明文
     * @param padMode 填充方式
     * @return 密文
     */
    public static byte[] desEdeCbcEncrypt(byte[] key, byte[] data, String padMode){
        byte[] res = null;
        String algorithm = "DESede/CBC/" + padMode;
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
//	        SecretKeySpec spec = getDesEdeKey(key);//附加密码修复
            SecretKeySpec spec = new SecretKeySpec(key, "DESede");
            IvParameterSpec ips = getIv(cipher.getBlockSize());
            data = padding(data, cipher.getBlockSize());
            cipher.init(Cipher.ENCRYPT_MODE, spec, ips);
            res = cipher.doFinal(data);
        } catch (Exception e) {
            logger.error("Fail: DesEde Cbc Encrypt",e);
        }
        return res;
    }

    /**
     * 3DES Cbc模式 解密
     * @param key 密钥
     * @param data 密文
     * @param padMode 填充方式
     * @return 明文
     */
    public static byte[] desEdeCbcDecrypt(byte[] key, byte[] data, String padMode){
        byte[] res = null;
        String algorithm = "DESede/CBC/" + padMode;
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
//			SecretKeySpec spec = getDesEdeKey(key);
            SecretKeySpec spec = new SecretKeySpec(key, "DESede");
            IvParameterSpec ips = getIv(cipher.getBlockSize());
            byte[] padData = padding(data, cipher.getBlockSize());
            cipher.init(Cipher.DECRYPT_MODE, spec, ips);
            res = cipher.doFinal(padData);
        } catch (Exception e) {
            logger.error("Fail: DesEde Cbc Decrypt",e);
        }
        return res;
    }

    /**
     * 初始化向量
     * @param len 长度
     * @return
     */
    public static IvParameterSpec getIv(int len) {
        //使用 IV 的例子是反馈模式中的密码，如，CBC 模式中的 DES 和使用 OAEP 编码操作的 RSA 密码
        byte[] zero = new byte[len];
        IvParameterSpec ivps = new IvParameterSpec(zero);
        return ivps;
    }

    /**
     * 补足长度
     * @param src
     * @param len
     * @return
     */
    public static byte[] padding(byte[] src, int len) {
        int paddingLength = len - src.length % len;
        if (len == paddingLength) {
            return src;
        }
        byte[] newsrc = new byte[src.length + paddingLength];
        System.arraycopy(src, 0, newsrc, 0, src.length);
        return newsrc;
    }


    /**
     * 自助生成对称密钥
     * */
    public static String getKey()throws Exception{
        // 以DES的方式初始化Key生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(168);// 设置密钥的长度为168位
        // 生成一个Key
        SecretKey generateKey = keyGenerator.generateKey();
        // 转变为字节数组
        byte[] encoded = generateKey.getEncoded();
        // 生成密钥字符串
        String encodeHexString = Hex.encodeHexString(encoded);
        return encodeHexString.substring(0,24);
    }

    public static void main(String[] args) throws Exception {
//        Base64.decodeBase64(privateKey);
//        Base64.encodeBase64String(publicKey)
        String NoPadding = "NoPadding";

        String data = "saaaaaa";
        String key = getKey();
        byte[]  byteDecEncrypt = desEdeCbcEncrypt(key.getBytes("UTF-8"), data.getBytes("UTF-8"), NoPadding);
        String decData= Base64.encodeBase64String(byteDecEncrypt);

        System.out.println(decData);
        byte[] retByte = desEdeCbcDecrypt(key.getBytes("UTF-8"),byteDecEncrypt,NoPadding);
        String d = Base64.encodeBase64String(retByte);
        System.out.println(d);
//        byte[] byteKey = Base64.decodeBase64(key);
//        byte[] byteData = Base64.decodeBase64(key);
//        byte[]  byteDecEncrypt = desEdeCbcEncrypt(byteKey, byteData, NoPadding);
//        String decData= Base64.encodeBase64String(byteDecEncrypt);
//
//        System.out.println(decData);
//        byte[] retByte = desEdeCbcDecrypt(byteKey,byteData,NoPadding);
//        String d = Base64.encodeBase64String(retByte);
//        System.out.println(d);

    }
}
