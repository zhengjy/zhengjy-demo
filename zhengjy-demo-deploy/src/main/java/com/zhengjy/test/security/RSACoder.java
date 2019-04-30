package com.zhengjy.test.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * 非对称加密算法RSA算法组件
 * 非对称算法一般是用来传送对称加密算法的密钥来使用的，相对于DH算法，RSA算法只需要一方构造密钥，不需要
 * 大费周章的构造各自本地的密钥对了。DH算法只能算法非对称算法的底层实现。而RSA算法算法实现起来较为简单
/**
 * Created by Jiyang.Zheng on 2019/1/24 16:46.
 */
public class RSACoder {
    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";


    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 1024;
    //公钥
    private static final String PUBLIC_KEY = "RSAPublicKey";

    //私钥
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 初始化密钥对
     *
     * @return Map 甲方密钥的Map
     */
    public static Map<String, Object> initKey() throws Exception {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //将密钥存储在map中
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;

    }


    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {

        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥map
     * @return byte[] 私钥
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥map
     * @return byte[] 公钥
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static byte[] getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key.getEncoded();
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static byte[] getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key.getEncoded();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        //初始化密钥
//        //生成密钥对
//        Map<String, Object> keyMap = RSACoder.initKey();
//        //公钥
//        byte[] publicKey = RSACoder.getPublicKey(keyMap);
//
//        //私钥
//        byte[] privateKey = RSACoder.getPrivateKey(keyMap);
//        System.out.println("公钥：" + Base64.encodeBase64String(publicKey));
//        System.out.println("私钥：" + Base64.encodeBase64String(privateKey));
//
//        System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");
//        String str = "站在大明门前守卫的禁卫军，事先没有接到\\n\" +\n" +
//                "                \"有关的命令，但看到大批盛装的官员来临，也就\\n\" +\n" +
//                "                \"以为确系举行大典，因而未加询问。进大明门即\\n\" +\n" +
//                "                \"为皇城。文武百官看到端门午门之前气氛平静，\\n\" +\n" +
//                "                \"城楼上下也无朝会的迹象，既无几案，站队点名\\n\" +\n" +
//                "                \"的御史和御前侍卫“大汉将军”也不见踪影，不免\\n\" +\n" +
//                "                \"心中揣测，互相询问：所谓午朝是否讹传？\";";
//        System.out.println("/n===========甲方向乙方发送加密数据==============");
//        System.out.println("原文:" + str);
//        //甲方进行数据的加密
//        byte[] code1 = RSACoder.encryptByPrivateKey(str.getBytes(), privateKey);
//        System.out.println("加密后的数据：" + Base64.encodeBase64String(code1));
//        System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");
//        //乙方进行数据的解密
//        byte[] decode1 = RSACoder.decryptByPublicKey(code1, publicKey);
//        System.out.println("乙方解密后的数据：" + new String(decode1) + "/n/n");
//
//        System.out.println("===========反向进行操作，乙方向甲方发送数据==============/n/n");


        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCO2p9FRC8IcJ/TFHuheSRFfJmjxIXXJeJfz59t97Ejqbu5E2JajCv6mOPMt8lJwhwfn+fXJvg7E5SAic9stpa7cRIcXCuhY4dZ59/6I6K6RWbB/akAI7MbamS26rdwnQ14FoinDSN3mTKn4l8r2AWajpkRvJEwEktbuXTKui1LwQIDAQAB";
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI7an0VELwhwn9MUe6F5JEV8maPEhdcl4l/Pn233sSOpu7kTYlqMK/qY48y3yUnCHB+f59cm+DsTlICJz2y2lrtxEhxcK6Fjh1nn3/ojorpFZsH9qQAjsxtqZLbqt3CdDXgWiKcNI3eZMqfiXyvYBZqOmRG8kTASS1u5dMq6LUvBAgMBAAECgYBrxNB879hPlK9uDTjLc1mCtmIt4xdZXPIvd7F9w2u2TNka4V3Ek5SmA+tuyIOpCjAvR3IepP6FitrwMYbzK7f/4VDDkpfVneh03SJ2Nr3due4Z5jT6uOKS8M3HZrgLOOlOIzKBQtADaNNlqhf5QNwiZVsxonRUvjoSkE0jSJvdwQJBAORcrGLu+iYlDR3W+d5RF6w/5e+ec33sP8nE/9QSG4U9tOBobVGdbjjHgeZvwJDPbfetqdLvBt74VsYEGEdSf5kCQQCgJKi2BpxOL5QbO64wmLhPaMEY370uPQ9GYUIopvgKmZynfg/gxEdknmrTqIbbQXyN5twZ4ZV48SSOCbn+gGZpAkAnTgwduyQzDI2FZUr8iNiaPSVGaO9X7SHNiKq/xZ4XANfxCAhI09/0xeIF15Y9VnaG+s0QETQNjJDi5DGJlRmBAkAqey8KAlzqXLK0OrOtjoWuAwLeP1Pm9SmdKOWs9QXtgexNfiJmJIsvxNpfxV/ov8lztx8B+pZ1G7rvtuLRAWypAkBPMQqSRl694kr0PPhPTZy+GoftsMiEY4T8pHHjcfgEvpqqGY4Kj82i0qBkM1UtMVu67SlY7R1WYpXSQ69jILs6";
        String str = "乙方向甲方发送数据RSA算法1223333";


        //甲方进行数据的加密
        byte[] code1 = RSACoder.encryptByPrivateKey(str.getBytes(), getPrivateKey(privateKey));
        System.out.println("加密后的数据：" + Base64.encodeBase64String(code1));
        System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");
        //乙方进行数据的解密
        byte[] decode1 = RSACoder.decryptByPublicKey(code1, getPublicKey(publicKey));
        System.out.println("乙方解密后的数据：" + new String(decode1) );


//        System.out.println("原文:" + str);
//        //乙方使用公钥对数据进行加密
//        byte[] code2 = RSACoder.encryptByPublicKey(str.getBytes(), getPublicKey(publicKey));
//        System.out.println("===========乙方使用公钥对数据进行加密==============");
//        System.out.println("加密后的数据：" + Base64.encodeBase64String(code2));
//        System.out.println("/n==================================================================");
//        System.out.println("=============乙方将数据传送给甲方======================");
//        System.out.println("===========甲方使用私钥对数据进行解密==============");
//
//        //甲方使用私钥对数据进行解密
//        byte[] decode2 = RSACoder.decryptByPrivateKey(code2, getPrivateKey(privateKey));
////        byte[] decode2 = RSACoder.decryptByPrivateKey(Base64.decodeBase64("gkpQhJFIZWgv/Wt6aRY+c6vc2Dtf8Rqb48mGvWaTEx1gXNp1BEO8XK06fi1fRIvI9L5tybZQCdGggGCH6ZGiTSRQOfG5Kw1Z3sL6Wx9rqIAe6a+fM0eI8ydc/9eoaTUyqdH/TQKk7Zjr/P+famHc1ey/ooeFvOCGh2yeisaCXr8="), getPrivateKey("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI7an0VELwhwn9MUe6F5JEV8maPEhdcl4l/Pn233sSOpu7kTYlqMK/qY48y3yUnCHB+f59cm+DsTlICJz2y2lrtxEhxcK6Fjh1nn3/ojorpFZsH9qQAjsxtqZLbqt3CdDXgWiKcNI3eZMqfiXyvYBZqOmRG8kTASS1u5dMq6LUvBAgMBAAECgYBrxNB879hPlK9uDTjLc1mCtmIt4xdZXPIvd7F9w2u2TNka4V3Ek5SmA+tuyIOpCjAvR3IepP6FitrwMYbzK7f/4VDDkpfVneh03SJ2Nr3due4Z5jT6uOKS8M3HZrgLOOlOIzKBQtADaNNlqhf5QNwiZVsxonRUvjoSkE0jSJvdwQJBAORcrGLu+iYlDR3W+d5RF6w/5e+ec33sP8nE/9QSG4U9tOBobVGdbjjHgeZvwJDPbfetqdLvBt74VsYEGEdSf5kCQQCgJKi2BpxOL5QbO64wmLhPaMEY370uPQ9GYUIopvgKmZynfg/gxEdknmrTqIbbQXyN5twZ4ZV48SSOCbn+gGZpAkAnTgwduyQzDI2FZUr8iNiaPSVGaO9X7SHNiKq/xZ4XANfxCAhI09/0xeIF15Y9VnaG+s0QETQNjJDi5DGJlRmBAkAqey8KAlzqXLK0OrOtjoWuAwLeP1Pm9SmdKOWs9QXtgexNfiJmJIsvxNpfxV/ov8lztx8B+pZ1G7rvtuLRAWypAkBPMQqSRl694kr0PPhPTZy+GoftsMiEY4T8pHHjcfgEvpqqGY4Kj82i0qBkM1UtMVu67SlY7R1WYpXSQ69jILs6"));
//
//        System.out.println("甲方解密后的数据：" + new String(decode2));
    }



}
