package com.etoak.test;

import com.google.common.collect.ImmutableSet;

import java.util.*;

import static com.etoak.test.TestEnum.Color.*;

/**
 * TestEnum
 * Created by xiao1 on 2017/7/23.
 */
public class TestEnum {

    enum Color {
        GREEN("绿色"), YELLOW("黄色"), RED("红色");

        private String desc;

        Color(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public static void main(String[] args) {
        System.out.println(EnumSet.of(GREEN, YELLOW, RED));
        System.out.println(EnumSet.allOf(Color.class));
        System.out.println(EnumSet.copyOf(Arrays.asList(Color.values())));

        Map<Color, Set<String>> map = new EnumMap<>(Color.class);
        System.out.println(map.size());
        for (Color test : Color.values()) {
            map.put(test, ImmutableSet.of(test.getDesc()));
        }
        System.out.println(map);
    }
}
