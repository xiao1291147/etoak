package com.etoak.asharp.test;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
public class GuavaTest {

    public static void main(String[] args) {
        joinerExec();
        System.out.println("hello");
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
