package com.demo.annotation.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * com.demo.annotation.test.Test
 *
 * @author xiaol
 * @date 2019/9/22
 */
public class Test {

    public static void main(String[] args) {
        // 查询id为10的用户
        Filter f1 = new Filter();
        f1.setId(10);

        // 查询name为lucy的用户
        Filter f2 = new Filter();
        f2.setUserName("lucy");

        // 查询邮箱为其中任意一个的用户
        Filter f3 = new Filter();
        f3.setEmail("liu@sina.com,zh@163.com,77777@qq.com");

        String sql1 = query(f1);
        String sql2 = query(f2);
        String sql3 = query(f3);
        System.out.println("sql1 = " + sql1);
        System.out.println("sql2 = " + sql2);
        System.out.println("sql3 = " + sql3);
    }

    private static String query(Object o) {
        StringBuilder builder = new StringBuilder();
        // 1. 获取到class
        Class<?> cls = o.getClass();
        // 2. 获取到table的名字
        boolean tableExist = cls.isAnnotationPresent(Table.class);
        if (!tableExist) {
            return builder.toString();
        }

        Table table = cls.getAnnotation(Table.class);
        builder.append("select * from ").append(table.value()).append(" where 1=1");
        // 3. 遍历所有的字段
        for (Field field : cls.getDeclaredFields()) {
            // 4. 处理每个字段对应的sql
            // 4.1 拿到字段名
            boolean columnExist = field.isAnnotationPresent(Column.class);
            if (!columnExist) {
                continue;
            }

            // 4.2 拿到字段值
            String fieldName = field.getName();
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Object fieldValue = null;
            try {
                Method method = cls.getMethod(getMethodName);
                fieldValue = method.invoke(o);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            // 4.3 拼接sql
            Column column = field.getAnnotation(Column.class);
            if (fieldValue instanceof Integer || fieldValue instanceof Long) {
                builder.append(" and ").append(column.value()).append("=").append(fieldValue);
            } else if (fieldValue instanceof String) {
                String str = (String) fieldValue;
                if (!str.contains(",")) {
                    builder.append(" and ").append(column.value()).append("='").append(fieldValue).append("'");
                    continue;
                }
                String inColumn = Arrays.stream(str.split(",")).map(s -> StringUtils.wrap(s, "'")).collect(Collectors.joining(","));
                builder.append(" and ").append(column.value()).append(" in (").append(inColumn).append(")");
            }
        }
        return builder.toString();
    }
}
