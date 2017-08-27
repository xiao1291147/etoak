package com.etoak.test;

import com.etoak.po.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.etoak.util.Utils.separator;
import static java.lang.System.out;

/**
 * TestStream
 * Created by xiao1 on 2017/8/21.
 */
public class TestStream {

    public static void main(String[] args) {
//        generate();
//        convert();
//        filter();
//        forEach();
//        findFirst();
//        reduce();
//        limitAndSkip();
//        sorted();
//        minAndMaxAndDistinct();
//        match();
        collect();
    }

    /**
     * 收集
     */
    private static void collect() {
        System.out.println("生成100个人信息, 按年龄分组");
        Stream.generate(newPersonSupplier()).limit(100).collect(Collectors.groupingBy(Person::getAge))
                .forEach((age, people) -> System.out.println("Age" + age + " = " + people.size()));

        separator();

        System.out.println("按照未成年人和成年人分组");
        Map<Boolean, List<Person>> children = Stream.generate(newPersonSupplier()).limit(100)
                .collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());
    }

    /**
     * 匹配
     */
    private static void match() {
        int[] ages = {10, 21, 34, 6, 55};
        System.out.println("All are adult? " + IntStream.rangeClosed(1, 5)
                .mapToObj(i -> new Person("name" + i, ages[i])).allMatch(p -> p.getAge() > 18));
        System.out.println("Any child? " + IntStream.rangeClosed(1, 5)
                .mapToObj(i -> new Person("name" + i, ages[i])).anyMatch(p -> p.getAge() < 16));
    }

    /**
     * 最大值/最小值/去重
     */
    private static void minAndMaxAndDistinct() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("D://file/lines"))) {
            System.out.println("找出最短一行的长度");
            reader.lines().peek(s -> System.out.println("min-" + s)).mapToInt(String::length).min().ifPresent(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        separator();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("D://file/lines"))) {
            System.out.println("找出最长一行的长度");
            reader.lines().peek(s -> System.out.println("max-" + s)).mapToInt(String::length).max().ifPresent(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        separator();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("D://file/lines"))) {
            System.out.println("找出全文的单词, 转小写, 并排序");
            reader.lines().peek(s -> System.out.println("lower/sorted-" + s)).flatMap(line -> Stream.of(line.split(" ")))
                    .filter(word -> word.length() > 0).map(String::toLowerCase).distinct().sorted().forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        separator();

        System.out.println("distinct");
        Stream.of(1, 2, 1, 2, 1).peek(i -> System.out.println("distinct-" + i)).distinct().forEach(out::println);
    }

    /**
     * 排序
     */
    private static void sorted() {
        System.out.println("sorted asc");
        List<Person> persons = IntStream.rangeClosed(1, 5).mapToObj(TestStream::newPerson).collect(Collectors.toList());
        System.out.println(persons.stream().limit(2).sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList()));

        separator();

        System.out.println("sorted desc");
        List<Person> persons2 = IntStream.rangeClosed(1, 5).mapToObj(TestStream::newPerson).collect(Collectors.toList());
        System.out.println(persons2.stream().limit(2).sorted((p1, p2) -> p2.getName().compareTo(p1.getName())).collect(Collectors.toList()));
    }

    /**
     * 限制/跳过
     */
    private static void limitAndSkip() {
        System.out.println("limit/skip short-circuiting");
        List<Person> persons = IntStream.rangeClosed(1, 1000).mapToObj(TestStream::newPerson).collect(Collectors.toList());
        System.out.println(persons.stream().map(Person::getName).limit(10).skip(3).collect(Collectors.toList()));

        separator();

        System.out.println("sorted limit not short-circuiting");
        List<Person> persons2 = IntStream.rangeClosed(1, 5).mapToObj(TestStream::newPerson).collect(Collectors.toList());
        System.out.println(persons2.stream().sorted(Comparator.comparing(Person::getName)).limit(2).collect(Collectors.toList()));
    }

    private static Person newPerson(int i) {
        return new Person("name" + i) {
            @Override
            public String getName() {
                System.out.println(super.getName());
                return super.getName();
            }
        };
    }

    /**
     * 归约
     */
    private static void reduce() {
        System.out.println("字符串连接, concat=ABCD");
        System.out.println(Stream.of("A", "B", "C", "D").reduce("", String::concat));
        System.out.println("求最小值, minValue=-3.0");
        System.out.println(Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min));
        System.out.println("求和, sumValue=10, 有起始值");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(0, Integer::sum));
        System.out.println("求和, sumValue=10, 无起始值");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum).get());
        System.out.println("过滤, 字符串连接, concat=ace");
        System.out.println(Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat));
    }

    /**
     * 查找第一个
     */
    private static void findFirst() {
        System.out.println("Optional");
        String str = null;
        System.out.println(Optional.ofNullable(str).map(String::length).orElse(-1));

        separator();

        System.out.println("findFirst");
        Arrays.asList(null, "a", "b", "c").parallelStream().filter(Objects::nonNull).findFirst().ifPresent(out::println);

        separator();

        System.out.println("findAny");
        Arrays.asList(null, "a", "b", "c").parallelStream().filter(Objects::nonNull).findAny().ifPresent(out::println);
    }

    /**
     * 遍历
     */
    private static void forEach() {
        System.out.println("打印男性姓名");
        Stream.of(new Person("Moss", 27, "M"), new Person("Jen", 29, "F"), new Person("Roy", 26, "M"))
                .filter(p -> Objects.equals(p.getSex(), Person.Sex.MALE.getSex())).forEach(p -> System.out.println(p.getName()));

        separator();

        System.out.println("peek每一个元素");
        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3).peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase).peek(e -> System.out.println("Mapped value: " + e)).forEach(out::println);
    }

    /**
     * 过滤
     */
    private static void filter() {
        System.out.println("留下偶数");
        IntStream.rangeClosed(0, 6).filter(n -> n % 2 == 0).forEach(out::println);

        separator();

        System.out.println("把单词挑出来");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("D://file/filter"))) {
            reader.lines().flatMap(line -> Stream.of(line.split(",")).filter(word -> word.length() > 0)).forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换
     */
    private static void convert() {
        System.out.println("转换大写");
        Arrays.asList("Moss", "Jen", "Roy").stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(out::println);

        separator();

        System.out.println("求平方数");
        Arrays.asList(1, 2, 3).stream().map(n -> n * n).forEach(out::println);

        separator();

        System.out.println("一对多转换");
        Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6)).flatMap(Collection::stream).forEach(out::println);
    }

    /**
     * 创建
     */
    private static void generate() {
        System.out.println("从Collection获取Stream");
        Arrays.asList(1, 2, 3).stream().forEach(integer -> System.out.print(integer + " "));
        System.out.println();
        Arrays.asList(4, 5, 6).parallelStream().forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        separator();

        System.out.println("从数组获取Stream");
        Arrays.stream(new int[]{1, 2, 3}).forEach(value -> System.out.print(value + " "));
        System.out.println();
        Stream.of(1, 2, 3).forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        separator();

        System.out.println("从BufferedReader获取Stream");

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("D://file/lines"))) {
            System.out.println(reader.lines().collect(Collectors.joining(System.lineSeparator())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        separator();

        System.out.println("从静态工厂获取Stream");
        System.out.println(IntStream.rangeClosed(1, 5).mapToObj(String::valueOf).reduce("", String::concat));
        try {
            Files.walk(Paths.get("D://file/date.txt")).forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        separator();

        System.out.println("自己创建Stream");
        Stream.generate(ThreadLocalRandom.current()::nextInt).limit(10).forEach(integer -> System.out.print(integer + " "));
        System.out.println();
        Stream.generate(newPersonSupplier()).limit(10).forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));
        Stream.iterate(0, n -> n + 3).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();

        separator();

        System.out.println("其他方式Stream");
        System.out.println(ThreadLocalRandom.current().ints(6, 0, 9)
                .mapToObj(String::valueOf).reduce("", String::concat));
        Pattern.compile(",").splitAsStream("hello,world").forEach(s -> System.out.print(s + " "));
        System.out.println();
        try {
            new JarFile(new File("target/test-1.0-SNAPSHOT.jar")).stream().limit(3).forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Supplier<Person> newPersonSupplier() {
        return new Supplier<Person>() {
            private int index = 0;

            @Override
            public Person get() {
                return new Person("StormTestUser" + index++, ThreadLocalRandom.current().nextInt(100));
            }
        };
    }
}
