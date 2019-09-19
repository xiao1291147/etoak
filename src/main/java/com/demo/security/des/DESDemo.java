package com.demo.security.des;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
import javax.crypto.spec.DESKeySpec;

/**
 * des对称加密算法
 *
 * @author xiaol
 * @date 2019/9/19
 */
public class DESDemo {

    public static void main(String[] args) {
        jdkDES();
        bcDES();
    }

    private static void jdkDES() {
        try {
            // 生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            System.out.println("keyGenerator.getProvider() = " + keyGenerator.getProvider());
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // KEY转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey convertKey = keyFactory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertKey);
            byte[] result = cipher.doFinal("com.demo.security.des.DESDemo".getBytes());
            System.out.println("jdk des encrypt = " + Hex.toHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertKey);
            result = cipher.doFinal(result);
            System.out.println("jdk des decrypt = " + new String(result));
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

    private static void bcDES() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            // 生成KEY，指定BC的provider
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
            System.out.println("keyGenerator.getProvider() = " + keyGenerator.getProvider());
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // KEY转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey convertKey = keyFactory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertKey);
            byte[] result = cipher.doFinal("com.demo.security.des.DESDemo".getBytes());
            System.out.println("bc des encrypt = " + Hex.toHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertKey);
            result = cipher.doFinal(result);
            System.out.println("bc des decrypt = " + new String(result));
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
