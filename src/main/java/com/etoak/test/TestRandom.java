package com.etoak.test;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * TestRandom
 * Created by xiaoliang.cui_c on 2017/9/4.
 */
public class TestRandom {

    public static void main(String[] args) {
        int len = 6;
        String randomNumeric = RandomStringUtils.randomNumeric(len);
        System.out.println("randomNumeric:" + randomNumeric);

        String randomAlphabetic = RandomStringUtils.randomAlphabetic(len);
        System.out.println("randomAlphabetic:" + randomAlphabetic);

        String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(len);
        System.out.println("randomAlphanumeric:" + randomAlphanumeric);

        String randomAscii = RandomStringUtils.randomAscii(len);
        System.out.println("randomAscii:" + randomAscii);

        String randomLowerLetters = RandomStringUtils.random(len, 'a', 'z' + 1, false, false);
        System.out.println("randomLowerLetters:" + randomLowerLetters);

        String randomUpperLetters = RandomStringUtils.random(len, 'A', 'Z' + 1, false, false);
        System.out.println("randomUpperLetters:" + randomUpperLetters);

        ThreadLocalRandom.current().ints(len, 0, 10).mapToObj(String::valueOf).reduce(String::concat).ifPresent(System.out::println);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(ThreadLocalRandom.current().nextInt(10));
        }
        System.out.println(builder);

        System.out.println("done!");
    }
}
