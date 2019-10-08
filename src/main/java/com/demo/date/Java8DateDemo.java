package com.demo.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Java8日期和时间
 * Instant：时间戳
 * Duration：持续时间，时间差
 * LocalDate：只包含日期
 * LocalTime：只包含时间
 * LocalDateTime：包含日期和时间
 * Period：时间段
 * ZoneOffset：时区偏移量
 * ZoneDateTime：带时区的时间
 * Clock：时钟，比如获取目前美国纽约的时间
 *
 * @author xiaol
 * @date 2019/10/9
 */
public class Java8DateDemo {

    public static void main(String[] args) {
        // 1. 获取今天的日期
        System.out.println("今天的日期：" + LocalDate.now());
        System.out.println();

        // 2. 指定日期，进行相应操作
        // 当月第一天
        System.out.println("当月第一天：" + LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("当月第一天2：" + LocalDate.now().withDayOfMonth(1));
        // 当月最后一天，不用考虑大月，小月，平年，闰年
        System.out.println("当月最后一天：" + LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
        // 当前日期 +1 天
        System.out.println("明天的日期：" + LocalDate.now().plusDays(1));
        // 判断是否为闰年
        System.out.println("今天是否为闰年：" + LocalDate.now().isLeapYear());
        System.out.println();

        // 3. 生日检查或者账单日检查
        LocalDate birthday = LocalDate.of(1991, 4, 23);
        MonthDay birthdayMd = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
        MonthDay today = MonthDay.from(LocalDate.of(2019, 4, 23));
        System.out.println("今天是否是生日：" + today.equals(birthdayMd));
        System.out.println();

        // 4. 获取当前时间
        System.out.println("当前时间：" + LocalTime.now());
        // 不显示毫秒
        System.out.println("当前时间：" + LocalTime.now().withNano(0));
        // 指定时间
        System.out.println("指定时间：" + LocalTime.of(23, 59, 59));
        System.out.println("指定时间：" + LocalTime.parse("00:00:00"));
        // 当前时间 +2 小时
        System.out.println("当前时间增加2小时：" + LocalTime.now().plusHours(2));
        System.out.println("当前时间增加2小时2：" + LocalTime.now().plus(2, ChronoUnit.HOURS));
        System.out.println();

        // 5. 日期前后比较
        System.out.println("2019年国庆过了吗：" + LocalDate.now().isAfter(LocalDate.of(2019, 10, 1)));
        System.out.println("2029年国庆过了吗：" + !LocalDate.now().isBefore(LocalDate.of(2019, 10, 1)));
        System.out.println();

        // 6. 白狐狸不同时区的时间
        // 查看当前的时区
        System.out.println("当前时区：" + ZoneId.systemDefault());
        // 查看美国纽约当前的时间

        System.out.println("上海时间：" + LocalDateTime.now());
        System.out.println("美国时间：" + LocalDateTime.now(ZoneId.of("America/New_York")));
        // 带有时区的时间
        System.out.println("美国时间带时区：" + ZonedDateTime.now(ZoneId.of("America/New_York")));
        System.out.println();

        // 7. 比较两个日期之间时间差
        Period period = Period.between(LocalDate.of(2019, 10, 1), LocalDate.now());
        System.out.println("距离2019国庆相对天数：" + period.getDays());
        System.out.println("距离2019国庆月数：" + period.getMonths());
        System.out.println("距离2019国庆天数：" + LocalDate.of(2019, 10, 1).until(LocalDate.now(), ChronoUnit.DAYS));
        System.out.println();

        // 8. 日期时间格式解析、格式化
        System.out.println("2019国庆：" + LocalDate.parse("20191001", DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("今天日期：" + DateTimeFormatter.ofPattern("YYYY MM dd").format(LocalDate.now()));
        System.out.println();

        // 9. java8时间类与Date类的相互转化
        // Date与Instant的相互转化
        System.out.println("当前时间戳：" + Instant.now());
        System.out.println("当前时间：" + Date.from(Instant.now()));
        System.out.println("当前时间戳：" + Date.from(Instant.now()).toInstant());
        // Date转为LocalDateTime
        System.out.println("当前时间：" + LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        // LocalDateTime转为Date
        System.out.println("当前时间：" + Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        // LocalDate转Date
        System.out.println("当前日期：" + Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
    }
}
