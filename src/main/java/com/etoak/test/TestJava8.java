package com.etoak.test;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.etoak.util.Utils.cutUp;

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
        Collection<String> collection = Lists.newArrayList("1", "2", "3", "4", "5");
        System.out.println(collection.stream().collect(Collectors.joining()));
        System.out.println(collection.parallelStream().collect(Collectors.joining()));

        cutUp();

        System.out.println("从数组获取Stream");
        String[] array = collection.toArray(new String[collection.size()]);
        System.out.println(Arrays.stream(array).collect(Collectors.joining()));
        System.out.println(Stream.of(array).collect(Collectors.joining()));

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
    }
}
