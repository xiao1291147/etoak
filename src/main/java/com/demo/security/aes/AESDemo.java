package com.demo.security.aes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES对称加密算法
 *
 * @author xiaol
 * @date 2019/9/19
 */
public class AESDemo {

    private static final String DATA = "com.demo.security.aes.AESDemo";

    public static void main(String[] args) {
        jdkAES();
        bcAES();
    }

    private static void jdkAES() {
        try {
            // 生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            System.out.println("keyGenerator.getProvider() = " + keyGenerator.getProvider());
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();
            System.out.println("key = " + Hex.toHexString(bytesKey));

            // KEY转换
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytesKey, "AES");

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(DATA.getBytes());
            System.out.println("jdk aes encryt = " + Hex.toHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            result = cipher.doFinal(result);
            System.out.println("jdk aes decrypt = " + new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    private static void bcAES() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            // 生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
            System.out.println("keyGenerator.getProvider() = " + keyGenerator.getProvider());
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();
            System.out.println("key = " + Hex.toHexString(bytesKey));

            // KEY转换
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytesKey, "AES");

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(DATA.getBytes());
            System.out.println("bc aes encryt = " + Hex.toHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            result = cipher.doFinal(result);
            System.out.println("bc aes decrypt = " + new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
}
