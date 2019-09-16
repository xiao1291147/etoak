package com.demo.security.dsa;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * dsa签名
 *
 * @author xiaol
 * @date 2019/9/16
 */
public class DSADemo {

    private static final String ALGORITHM_DSA = "DSA";
    private static final String DATA = "com.demo.security.dsa.DSADemo";
    private static final String SHA_1_WITH_DSA = "SHA1withDSA";

    public static void main(String[] args) {
        try {
            // 1. 初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_DSA);
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
            DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();

            // 2. 执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
            KeyFactory privateKeyFactory = KeyFactory.getInstance(ALGORITHM_DSA);
            PrivateKey privateKey = privateKeyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature privateSignature = Signature.getInstance(SHA_1_WITH_DSA);
            privateSignature.initSign(privateKey);
            privateSignature.update(DATA.getBytes());
            byte[] sign = privateSignature.sign();
            System.out.println("jdk dsa sign = " + Base64.getEncoder().encodeToString(sign));

            // 3. 验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
            KeyFactory publicKeyFactory = KeyFactory.getInstance(ALGORITHM_DSA);
            PublicKey publicKey = publicKeyFactory.generatePublic(x509EncodedKeySpec);
            Signature publicSignature = Signature.getInstance(SHA_1_WITH_DSA);
            publicSignature.initVerify(publicKey);
            publicSignature.update(DATA.getBytes());
            boolean verify = publicSignature.verify(sign);
            System.out.println("jdk dsa verify = " + verify);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }
}
