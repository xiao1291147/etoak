package com.etoak.test;

import com.etoak.po.Book;
import com.etoak.po.Person;
import com.google.common.base.*;
import com.google.common.collect.*;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/**
 * Getting Started with Google Guava
 * Created by xiao1 on 2017/7/20.
 */
public class TestGuava {

    public static void main(String[] args) {
//        joinerExec();
//        splitterExec();
//        charsetsExec();
//        stringsExec();
//        charMatcherExec();
//        preconditionsExec();
//        objectsExec();
//        functionExec();
        fluentIterableExec();
    }

    private static void fluentIterableExec() {
        Person person1 = new Person("Wilma", 30, "F");
        Person person2 = new Person("Fred", 32, "M");
        Person person3 = new Person("Betty", 31, "F");
        Person person4 = new Person("Barney", 33, "M");
        List<Person> personList = Lists.newArrayList(person1, person2, person3, person4);
        Iterable<Person> personsFilteredByAge = FluentIterable.from(personList).filter(new Predicate<Person>() {
            @Override
            public boolean apply(Person input) {
                return input.getAge() > 31;
            }
        });

        Assert.assertThat(Iterables.contains(personsFilteredByAge, person2), CoreMatchers.is(true));
        Assert.assertThat(Iterables.contains(personsFilteredByAge, person4), CoreMatchers.is(true));
        Assert.assertThat(Iterables.contains(personsFilteredByAge, person1), CoreMatchers.is(false));
        Assert.assertThat(Iterables.contains(personsFilteredByAge, person3), CoreMatchers.is(false));
        personsFilteredByAge.forEach(out::println);


        List<String> transformedPersonList = FluentIterable.from(personList).transform(new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return Joiner.on('#').join(input.getName(), input.getAge());
            }
        }).toList();

        Assert.assertThat(transformedPersonList.get(1), CoreMatchers.is("Fred#32"));
        transformedPersonList.forEach(out::println);
    }

    private static void functionExec() {
        Function<Date, String> function = new Function<Date, String>() {
            @Override
            public String apply(Date input) {
                return new SimpleDateFormat("dd/mm/yyyy").format(input);
            }
        };
        System.out.println(function.apply(new Date()));
    }

    private static void objectsExec() {
        Book book = new Book(new Person("name"), "title", "publisher", "isbn", 12.11);
        System.out.println(book.equals(new Book(new Person("name"), "title", "publisher", "isbn", 12.1)));
        System.out.println(book.hashCode());
        System.out.println(book.compareTo(new Book(new Person("name"), "title", "publisher", "isbn", 12.1)));

        System.out.println();

        System.out.println(book.equals(new Book(new Person("name2"), "title", "publisher", "isbn", 12.11)));
        System.out.println(book.hashCode());
        System.out.println(book.compareTo(new Book(new Person("name2"), "title", "publisher", "isbn", 12.11)));

        System.out.println();

        System.out.println(book.equals(new Book(new Person("name"), "title", "publisher", "isbn", 12.11)));
        System.out.println(book.hashCode());
        System.out.println(book.compareTo(new Book(new Person("name"), "title", "publisher", "isbn", 12.11)));
    }

    private static void preconditionsExec() {
        boolean condition = false;
        try {
            Preconditions.checkArgument(condition, "1", "2", "3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Preconditions.checkState(condition, "1", "2", "3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String str = null;
        try {
            Preconditions.checkNotNull(str, "1", "2", "3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Preconditions.checkElementIndex(2, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Preconditions.checkPositionIndexes(0, 2, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void charMatcherExec() {
        String tabsAndSpaces = "String  with    spaces    and  tabs";
        System.out.println(CharMatcher.whitespace().collapseFrom(tabsAndSpaces, ' '));

        tabsAndSpaces = "    String with   spaces    and   tabs";
        System.out.println(CharMatcher.whitespace().trimAndCollapseFrom(tabsAndSpaces, ' '));

        String lettersAndNumbers = "\"foo989yxbar234\"";
        System.out.println(CharMatcher.javaDigit().retainFrom(lettersAndNumbers));

        lettersAndNumbers = "foo989 xbar 234";
        System.out.println(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom(lettersAndNumbers));
    }

    private static void stringsExec() {
        StringBuilder builder = new StringBuilder("foo");
        char c = 'x';
        for (int i = 0; i < 3; i++) {
            builder.append(c);
        }
        System.out.println(builder);
        System.out.println(Strings.padEnd("foo", 6, 'x'));
        for (int i = 0; i < 10000; i++) {
            String str = Integer.toString(i);
            System.out.println(Strings.padStart(str, 5, '0'));
        }

        System.out.println(Strings.repeat("0", 10));
        System.out.println(Strings.isNullOrEmpty(null));
        System.out.println(Strings.isNullOrEmpty(""));
        System.out.println(Strings.isNullOrEmpty("isNullOrEmpty"));
        System.out.println(Strings.nullToEmpty(null));
        System.out.println(Strings.nullToEmpty(""));
        System.out.println(Strings.nullToEmpty("nullToEmpty"));
        System.out.println(Strings.emptyToNull(null));
        System.out.println(Strings.emptyToNull(""));
        System.out.println(Strings.emptyToNull("emptyToNull"));
    }

    private static void charsetsExec() {
        System.out.println(Charsets.ISO_8859_1);
        System.out.println(Charsets.US_ASCII);
        System.out.println(Charsets.UTF_8);
        System.out.println(Charsets.UTF_16);
        System.out.println(Charsets.UTF_16BE);
        System.out.println(Charsets.UTF_16LE);
    }

    private static void splitterExec() {
        Splitter on = Splitter.on("#");
        for (String str : on.trimResults().split("1 #2#3 #4")) {
            System.out.println(str);
        }

        for (String str : on.split("1#   2#   3#")) {
            System.out.println(str);
        }

        String startString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C", "Redskins");
        testMap.put("New York City", "Giants");
        testMap.put("Philadelphia", "Eagles");
        testMap.put("Dallas", "Cowboys");
        Map<String, String> splitMap = Splitter.on("#").withKeyValueSeparator("=").split(startString);
        System.out.println(testMap);
        System.out.println(splitMap);
        Assert.assertThat(testMap, CoreMatchers.is(splitMap));
    }

    private static void joinerExec() {
        String delimiter = "|";
        List<Integer> list = Lists.newArrayList(1, 2, 3, null, 4, 5);

        noJoiner(list, delimiter);
        joiner(list, delimiter);
    }

    private static void joiner(List<Integer> list, String delimiter) {
        System.out.println(Joiner.on(delimiter).skipNulls().join(list));
        System.out.println(Joiner.on(delimiter).skipNulls().appendTo(new StringBuilder(), list));
        System.out.println(Joiner.on(delimiter).useForNull("missing").join(list));

        try (FileWriter fileWriter = new FileWriter(new File(("D://file/date.txt")))) {
            List<LocalDate> dateList = getDates();
            Joiner.on("#").useForNull(" ").appendTo(fileWriter, dateList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String joinMap = Joiner.on("&").withKeyValueSeparator("=").join(ImmutableMap.of("name", "sheldon", "age", "23", "sex", "S"));
        System.out.println(joinMap);

        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("name", "penny");
        map.put("age", "25");
        map.put("addr", null);
        map.put(null, "M");
        map.put(null, null);
        System.out.println(Joiner.on("&").useForNull("missing").withKeyValueSeparator("=").join(map));
    }

    private static List<LocalDate> getDates() {
        LocalDate today = LocalDate.now();
        List<LocalDate> dateList = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            dateList.add(today.plusDays(i));
            dateList.add(null);
        }
        System.out.println(dateList);
        return dateList;
    }

    private static void noJoiner(List<Integer> list, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (Integer i : list) {
            if (i != null) {
                builder.append(i).append(delimiter);
            }
        }
        builder.setLength(builder.length() - delimiter.length());
        System.out.println(builder);
    }
}

