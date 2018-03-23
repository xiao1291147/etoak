package com.etoak.test;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

/**
 * TestRandom
 * Created by xiaoliang.cui_c on 2017/9/4.
 */
public class TestRandom {

    public static void main(String[] args) {
        int len = 10;
        String randomNumeric = RandomStringUtils.randomNumeric(len);
        System.out.println("randomNumeric:" + randomNumeric);
        RandomStringGenerator numericRandom = new RandomStringGenerator.Builder().filteredBy(CharacterPredicates.ARABIC_NUMERALS).build();
        System.out.println("numericRandom = " + numericRandom.generate(len));
        System.out.println();

        String randomAlphabetic = RandomStringUtils.randomAlphabetic(len);
        System.out.println("randomAlphabetic:" + randomAlphabetic);
        RandomStringGenerator alphabeticRandom = new RandomStringGenerator.Builder().filteredBy(CharacterPredicates.ASCII_LETTERS).build();
        System.out.println("alphabeticRandom = " + alphabeticRandom.generate(len));
        System.out.println();

        String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(len);
        System.out.println("randomAlphanumeric:" + randomAlphanumeric);
        RandomStringGenerator alphanumericRandom = new RandomStringGenerator.Builder().filteredBy(CharacterPredicates.ASCII_ALPHA_NUMERALS).build();
        System.out.println("alphanumericRandom = " + alphanumericRandom.generate(len));
        System.out.println();

        String randomAscii = RandomStringUtils.randomAscii(len);
        System.out.println("randomAscii:" + randomAscii);
        RandomStringGenerator asciiRandom = new RandomStringGenerator.Builder().withinRange(32, 127).build();
        System.out.println("asciiRandom = " + asciiRandom.generate(len));
        System.out.println();

        String randomLowerLetters = RandomStringUtils.random(len, 'a', 'z' + 1, false, false);
        System.out.println("randomLowerLetters:" + randomLowerLetters);
        RandomStringGenerator lowerLettersRandom = new RandomStringGenerator.Builder().filteredBy(CharacterPredicates.ASCII_LOWERCASE_LETTERS)
                .build();
        System.out.println("lowerLettersRandom = " + lowerLettersRandom.generate(len));
        System.out.println();

        String randomUpperLetters = RandomStringUtils.random(len, 'A', 'Z' + 1, false, false);
        System.out.println("randomUpperLetters:" + randomUpperLetters);
        RandomStringGenerator upperLettersRandom = new RandomStringGenerator.Builder().filteredBy(CharacterPredicates.ASCII_UPPERCASE_LETTERS)
                .build();
        System.out.println("upperLettersRandom = " + upperLettersRandom.generate(len));
        System.out.println();

        ThreadLocalRandom.current().ints(len, 0, 10)
                .mapToObj(String::valueOf).reduce(String::concat).ifPresent(System.out::println);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(ThreadLocalRandom.current().nextInt(10));
        }
        System.out.println(builder);
        System.out.println();

        RandomStringGenerator selectFromRandom = new RandomStringGenerator.Builder().selectFrom('1', '3', '5', '7', '9').build();
        System.out.println("selectFromRandom = " + selectFromRandom.generate(len));
        System.out.println();

        RandomStringGenerator withinRangeRandom = new RandomStringGenerator.Builder().withinRange('1', '2').build();
        System.out.println("withinRangeRandom = " + withinRangeRandom.generate(len));
        System.out.println();

        System.out.println("done!");
    }
}
