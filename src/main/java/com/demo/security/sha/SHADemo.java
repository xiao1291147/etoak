package com.demo.security.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * sha摘要算法
 *
 * @author xiaol
 * @date 2019/9/17
 */
public class SHADemo {

    private static final String DATA = "com.demo.security.sha.SHADemo";

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        jdkSHA1();
        bcSHA1();
        bcSHA224();
        ccSHA();
    }

    private static void jdkSHA1() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            System.out.println("SHA1.getProvider() = " + md.getProvider());
            md.update(DATA.getBytes());
            byte[] bytes = md.digest();
            System.out.println("JDK SHA1 = " + Hex.encodeHexString(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void bcSHA1() {
        Digest digest = new SHA1Digest();
        digest.update(DATA.getBytes(), 0, DATA.getBytes().length);
        byte[] bytes = new byte[digest.getDigestSize()];
        digest.doFinal(bytes, 0);
        System.out.println("BC SHA1 = " + org.bouncycastle.util.encoders.Hex.toHexString(bytes));
    }

    private static void bcSHA224() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA224");
            System.out.println("SHA224.getProvider() = " + md.getProvider());
            md.update(DATA.getBytes());
            byte[] bytes = md.digest();
            System.out.println("BC SHA224 = " + Hex.encodeHexString(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void ccSHA() {
        System.out.println("DigestUtils.getSha1Digest().getProvider() = " + DigestUtils.getSha1Digest().getProvider());
        System.out.println("CC SHA1 = " + DigestUtils.sha1Hex(DATA));
        System.out.println("DigestUtils.getSha256Digest().getProvider() = " + DigestUtils.getSha256Digest().getProvider());
        System.out.println("CC SHA256 = " + DigestUtils.sha256Hex(DATA));
        System.out.println("DigestUtils.getSha384Digest().getProvider() = " + DigestUtils.getSha384Digest().getProvider());
        System.out.println("CC SHA384 = " + DigestUtils.sha384Hex(DATA));
        System.out.println("DigestUtils.getSha512Digest().getProvider() = " + DigestUtils.getSha512Digest().getProvider());
        System.out.println("CC SHA512 = " + DigestUtils.sha512Hex(DATA));
        System.out.println("DigestUtils.getSha3_224Digest().getProvider() = " + DigestUtils.getSha3_224Digest().getProvider());
        System.out.println("CC SHA3_224 = " + DigestUtils.sha3_224Hex(DATA));
        System.out.println("DigestUtils.getSha3_256Digest().getProvider() = " + DigestUtils.getSha3_256Digest().getProvider());
        System.out.println("CC SHA3_256 = " + DigestUtils.sha3_256Hex(DATA));
        System.out.println("DigestUtils.getSha3_384Digest().getProvider() = " + DigestUtils.getSha3_384Digest().getProvider());
        System.out.println("CC SHA3_384 = " + DigestUtils.sha3_384Hex(DATA));
        System.out.println("DigestUtils.getSha3_512Digest().getProvider() = " + DigestUtils.getSha3_512Digest().getProvider());
        System.out.println("CC SHA3_512 = " + DigestUtils.sha3_512Hex(DATA));
    }
}
