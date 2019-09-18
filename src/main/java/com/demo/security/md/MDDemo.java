package com.demo.security.md;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * md摘要算法
 *
 * @author xiaol
 * @date 2019/9/17
 */
public class MDDemo {

    private static final String DATA = "com.demo.security.md.MDDemo";

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        jdkMD2();
        bcMD2();
        bcMD4();
        bcMD5();
        jdkMD5();
        ccMD2();
        ccMD5();
    }

    private static void jdkMD2() {
        try {
            MessageDigest md2 = MessageDigest.getInstance("MD2");
            System.out.println("md2.getProvider() = " + md2.getProvider());
            byte[] md5Bytes = md2.digest(DATA.getBytes());
            System.out.println("JDK MD2 = " + Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void bcMD2() {
        Digest digest = new MD2Digest();
        digest.update(DATA.getBytes(), 0, DATA.getBytes().length);
        byte[] bytes = new byte[digest.getDigestSize()];
        digest.doFinal(bytes, 0);
        System.out.println("BC MD2 = " + org.bouncycastle.util.encoders.Hex.toHexString(bytes));
    }

    private static void bcMD4() {
        try {
            MessageDigest md4 = MessageDigest.getInstance("MD4");
            System.out.println("md4.getProvider() = " + md4.getProvider());
            byte[] md4Bytes = md4.digest(DATA.getBytes());
            System.out.println("BC MD4 = " + Hex.encodeHexString(md4Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void bcMD5() {
        Digest digest = new MD5Digest();
        digest.update(DATA.getBytes(), 0, DATA.getBytes().length);
        byte[] bytes = new byte[digest.getDigestSize()];
        digest.doFinal(bytes, 0);
        System.out.println("BC MD5 = " + org.bouncycastle.util.encoders.Hex.toHexString(bytes));
    }

    private static void jdkMD5() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            System.out.println("md5.getProvider() = " + md5.getProvider());
            byte[] md5Bytes = md5.digest(DATA.getBytes());
            System.out.println("JDK MD5 = " + Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void ccMD2() {
        System.out.println("DigestUtils.getMd2Digest().getProvider() = " + DigestUtils.getMd2Digest().getProvider());
        System.out.println("CC MD2 = " + DigestUtils.md2Hex(DATA));
    }

    private static void ccMD5() {
        System.out.println("DigestUtils.getMd5Digest().getProvider() = " + DigestUtils.getMd5Digest().getProvider());
        System.out.println("CC MD5 = " + DigestUtils.md5Hex(DATA));
    }
}
