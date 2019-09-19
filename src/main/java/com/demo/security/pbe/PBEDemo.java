package com.demo.security.pbe;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * PBE对称加密算法
 *
 * @author xiaol
 * @date 2019/9/19
 */
public class PBEDemo {

    public static void main(String[] args) {
        jdkPBE();
    }

    private static void jdkPBE() {
        try {
            // 初始化盐
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = secureRandom.generateSeed(8);
            System.out.println("salt = " + Base64.encodeBase64String(salt));

            // 口令与密钥
            String password = "PBEDemo";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            SecretKey secretKey = keyFactory.generateSecret(pbeKeySpec);

            // 加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
            byte[] result = cipher.doFinal("com.demo.security.pbe.PBEDemo".getBytes());
            System.out.println("jdk pbe encrypt = " + Base64.encodeBase64String(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
            result = cipher.doFinal(result);
            System.out.println("jdk pbe decrypt = " + new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
