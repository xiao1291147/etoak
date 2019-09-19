package com.demo.security.des;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * 3DES对称加密算法
 *
 * @author xiaol
 * @date 2019/9/19
 */
public class DESedeDemo {

    public static void main(String[] args) {
        jdk3DES();
        bc3DES();
    }

    private static void jdk3DES() {
        try {
            // 生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            System.out.println("keyGenerator.getProvider() = " + keyGenerator.getProvider());
            // keyGenerator.init(168);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // KEY转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey convertKey = keyFactory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertKey);
            byte[] result = cipher.doFinal("com.demo.security.des.DESDemo".getBytes());
            System.out.println("jdk 3des encrypt = " + Hex.toHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertKey);
            result = cipher.doFinal(result);
            System.out.println("jdk 3des decrypt = " + new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private static void bc3DES() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            // 生成KEY，指定BC的provider
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede", "BC");
            System.out.println("keyGenerator.getProvider() = " + keyGenerator.getProvider());
            keyGenerator.init(168);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // KEY转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey convertKey = keyFactory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertKey);
            byte[] result = cipher.doFinal("com.demo.security.des.DESDemo".getBytes());
            System.out.println("bc 3des encrypt = " + Hex.toHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertKey);
            result = cipher.doFinal(result);
            System.out.println("bc 3des decrypt = " + new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
}
