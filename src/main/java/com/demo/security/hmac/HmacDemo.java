package com.demo.security.hmac;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * hmac摘要算法
 *
 * @author xiaol
 * @date 2019/9/17
 */
public class HmacDemo {

    private static final String DATA = "com.demo.security.hmac.HmacDemo";

    public static void main(String[] args) {
        jdkHmacMD5();
        bcHmacMD5();
    }

    private static void jdkHmacMD5() {
        try {
            // 1. 初始化KeyGenerator
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
            // 2. 产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 3. 获得密钥
            // byte[] secretKeyEncoded = secretKey.getEncoded();

            byte[] secretKeyEncoded = Hex.decodeHex(new char[]{'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'});
            // 4. 还原密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyEncoded, "HmacMD5");
            // 5. 实例化MAC
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            // 6. 初始化Mac
            mac.init(secretKeySpec);
            // 7. 执行摘要
            byte[] bytes = mac.doFinal(DATA.getBytes());
            System.out.println("jdk hmacMD5 = " + Hex.encodeHexString(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }

    private static void bcHmacMD5() {
        HMac hMac = new HMac(new MD5Digest());
        hMac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaaaaaaaa")));
        hMac.update(DATA.getBytes(), 0, DATA.getBytes().length);

        byte[] bytes = new byte[hMac.getMacSize()];
        hMac.doFinal(bytes, 0);
        System.out.println("bc hmacMD5 = " + org.bouncycastle.util.encoders.Hex.toHexString(bytes));
    }
}
