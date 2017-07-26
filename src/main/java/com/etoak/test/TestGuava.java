package com.etoak.test;

import com.google.common.base.*;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        objectsExec();
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

class Person implements Comparable<Person> {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public int compareTo(Person o) {
        return ComparisonChain.start()
                .compare(this.name, o.getName())
                .result();
    }

    public String getName() {
        return name;
    }
}

class Book implements Comparable<Book> {
    private Person author;
    private String title;
    private String publisher;
    private String isbn;
    private double price;

    public Book(Person author, String title, String publisher, String isbn, double price) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 &&
                Objects.equal(author, book.author) &&
                Objects.equal(title, book.title) &&
                Objects.equal(publisher, book.publisher) &&
                Objects.equal(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(author, title, publisher, isbn, price);
    }

    @Override
    public int compareTo(Book o) {
        return ComparisonChain.start()
                .compare(this.title, o.getTitle())
                .compare(this.author, o.getAuthor())
                .compare(this.publisher, o.getPublisher())
                .compare(this.isbn, o.getIsbn())
                .compare(this.price, o.getPrice())
                .result();
    }

    public Person getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }
}
