package com.etoak.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.etoak.util.Utils.cutUp;
import static java.lang.System.out;

/**
 * TestJava8
 * Created by xiao1 on 2017/8/21.
 */
public class TestJava8 {

    public static void main(String[] args) {
        generateStream();
    }

    private static void generateStream() {
        System.out.println("从Collection获取Stream");
        Arrays.asList(1, 2, 3).stream().forEach(out::println);
        Arrays.asList(4, 5, 6).parallelStream().forEach(out::println);

        cutUp();

        System.out.println("从数组获取Stream");
        Arrays.stream(new int[]{1, 2, 3}).forEach(out::println);
        Stream.of(1, 2, 3).forEach(out::println);

        cutUp();

        System.out.println("从BufferedReader获取Stream");
        try (Stream<String> lines = Files.newBufferedReader(Paths.get("D://file/date.txt")).lines()) {
            System.out.println(lines.collect(Collectors.joining()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cutUp();

        System.out.println("从静态工厂获取Stream");
        System.out.println(IntStream.rangeClosed(1, 5).mapToObj(String::valueOf).collect(Collectors.joining()));
        try {
            Files.walk(Paths.get("D://file/date.txt")).forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cutUp();

        System.out.println("自己构建Stream");
        System.out.println(ThreadLocalRandom.current().ints(6, 0, 9).mapToObj(String::valueOf).collect(Collectors.joining()));
        Pattern.compile(",").splitAsStream("hello,world").forEach(out::println);
    }
}
