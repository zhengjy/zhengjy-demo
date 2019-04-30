//package com.zhengjy.test.security;
//
//
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
///**
// * Created by Jiyang.Zheng on 2019/1/25 13:28.
// */
//public class TripleDESUtil {
//    /**
//     * 生成密钥
//     *
//     * @return 密钥
//     * @throws Exception
//     */
//    public static byte[] initKey() throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//        keyGenerator.init(168);//密钥长度 112 168
//        SecretKey secretKey = keyGenerator.generateKey();
//        return secretKey.getEncoded();
//    }
//
//    /**
//     * 3DES加密
//     *
//     * @param data 要加密的数据
//     * @param key  加密所使用的密钥
//     * @return 加密后的数据
//     * @throws Exception
//     */
//    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
//        SecretKey secretKey = new SecretKeySpec(key, "DESede");
//        Cipher cipher = Cipher.getInstance("DESede");
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//        return cipher.doFinal(data);
//    }
//
//    /**
//     * 3DES解密
//     *
//     * @param data 加密后的数据
//     * @param key  解密所需要的key
//     * @return 解密后的数据
//     * @throws Exception
//     */
//    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
//        SecretKey secretKey = new SecretKeySpec(key, "DESede");
//        Cipher cipher = Cipher.getInstance("DESede");
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        return cipher.doFinal(data);
//    }
//    private static final String DATA = "asiatravel";
//    private static final String TAG = "TAG";
//
//    public static void main(String[] args) {
//        /**
//         * DES 加密
//         */
//        byte[] desKey = DESUtil.initKey();
//        System.out.println("DES key: " + ByteToHexUtil.fromByteToHex(desKey));
//        /**
//         * 加密后的数据
//         */
//        byte[] desResult = DESUtil.encrypt(DATA.getBytes(), desKey);
//        System.out.println(DATA + "DES 加密>>>" + ByteToHexUtil.fromByteToHex(desResult));
//
//        /**
//         * DES 解密
//         */
//        byte[] decryptResult = DESUtil.decrypt(desResult, desKey);
//        System.out.println(DATA + "DES 解密" + new String(decryptResult));
//
//
//    }
//}
