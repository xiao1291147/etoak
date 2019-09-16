package com.demo.security.ecdsa;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * ecdsa签名
 *
 * @author xiaol
 * @date 2019/9/16
 */
public class ECDSADemo {

    private static final String ALGORITHM_EC = "EC";
    private static final String DATA = "com.demo.security.ecdsa.ECDSADemo";
    private static final String SHA_1_WITH_ECDSA = "SHA1withECDSA";

    public static void main(String[] args) {
        try {
            // 1. 初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_EC);
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();

            // 2. 执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
            KeyFactory privateKeyFactory = KeyFactory.getInstance(ALGORITHM_EC);
            PrivateKey privateKey = privateKeyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature privateSignature = Signature.getInstance(SHA_1_WITH_ECDSA);
            privateSignature.initSign(privateKey);
            privateSignature.update(DATA.getBytes());
            byte[] sign = privateSignature.sign();
            System.out.println("jdk ecdsa sign = " + Base64.getEncoder().encodeToString(sign));

            // 3. 验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
            KeyFactory publicKeyFactory = KeyFactory.getInstance(ALGORITHM_EC);
            PublicKey publicKey = publicKeyFactory.generatePublic(x509EncodedKeySpec);
            Signature publicSignature = Signature.getInstance(SHA_1_WITH_ECDSA);
            publicSignature.initVerify(publicKey);
            publicSignature.update(DATA.getBytes());
            boolean verify = publicSignature.verify(sign);
            System.out.println("jdk ecdsa verify = " + verify);
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
