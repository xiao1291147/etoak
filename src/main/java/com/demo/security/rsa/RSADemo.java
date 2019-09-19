package com.demo.security.rsa;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * rsa签名
 *
 * @author xiaol
 * @date 2019/9/16
 */
public class RSADemo {

    private static final String ALGORITHM_RSA = "RSA";
    private static final String SIGN_ALGORITHM_MD5 = "MD5withRSA";
    private static final String DATA = "com.demo.security.rsa.RSADemo";

    public static void main(String[] args) {
        try {
            // 1. 初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("rsaPrivateKey = " + Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));
            System.out.println("rsaPublicKey = " + Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));

            // 2. 执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory privateKeyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PrivateKey privateKey = privateKeyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature privateSignature = Signature.getInstance(SIGN_ALGORITHM_MD5);
            privateSignature.initSign(privateKey);
            privateSignature.update(DATA.getBytes());
            byte[] result = privateSignature.sign();
            System.out.println("jdk rsa sign = " + Base64.getEncoder().encodeToString(result));
            // 私钥加密、公钥解密-加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] encryptResult = cipher.doFinal(DATA.getBytes());
            System.out.println("私钥加密、公钥解密-加密 = " + Base64.getEncoder().encodeToString(encryptResult));

            // 3. 验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory publicKeyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PublicKey publicKey = publicKeyFactory.generatePublic(x509EncodedKeySpec);
            Signature publicSignature = Signature.getInstance(SIGN_ALGORITHM_MD5);
            publicSignature.initVerify(publicKey);
            publicSignature.update(DATA.getBytes());
            boolean verify = publicSignature.verify(result);
            System.out.println("jdk rsa verify = " + verify);
            // 私钥加密、公钥解密-解密
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] decryptResult = cipher.doFinal(encryptResult);
            System.out.println("私钥加密、公钥解密-解密 = " + new String(decryptResult));

            // 公钥加密、私钥解密-加密
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptResult = cipher.doFinal(DATA.getBytes());
            System.out.println("公钥加密、私钥解密-加密 = " + Base64.getEncoder().encodeToString(decryptResult));

            // 公钥加密、私钥解密-解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptResult = cipher.doFinal(encryptResult);
            System.out.println("公钥加密、私钥解密-加密 = " + new String(decryptResult));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
