package com.etoak.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * TODO_X
 * @author xiao1
 * @date 2018/8/24
 */
public class TestLog {
    private static Pattern patternStart = Pattern.compile("(?<=startTime:)(\\d+?)(?= )");
    private static Pattern patternEnd = Pattern.compile("(?<=endTime:)(\\d+?)(?= )");
    private static Pattern patternCreateAt = Pattern.compile("(?<=createAt:)(.+? .+?)(?= )");
    private static Pattern patternCode = Pattern.compile("(?<=\"code\":\")(.+?)(?=\")");
    private static Pattern patternStoreId = Pattern.compile("(?<=\"storeId\":\")(.+?)(?=\")");
    private static Pattern patternStationId = Pattern.compile("(?<=\"stationId\":\")(.+?)(?=\")");
    private static AtomicInteger integer = new AtomicInteger(1);

    public static void main(String[] args) throws Exception {
        // Collection<String> collection = new ArrayList<>();
        Collection<String> collection = new TreeSet<>(Comparator.reverseOrder());

        Files.readAllLines(Paths.get("/file", "log.txt")).parallelStream().forEach(s -> {
            getMsg(collection, s);
            // getMsg2(collection, s);
        });

        Files.write(Paths.get("/file", "resultLog.csv"), collection);
    }

    private static void getMsg(Collection<String> collection, String s) {
        JSONArray jsonArray = JSON.parseObject(s).getJSONArray("responses");
        jsonArray.forEach(o -> {
            JSONArray hitsArray = JSON.parseObject(o.toString()).getJSONObject("hits").getJSONArray("hits");
            System.out.println(hitsArray.size());
            hitsArray.forEach(o1 -> {
                String message = JSON.parseObject(o1.toString()).getJSONObject("_source").getString("message");
                getMsg2(collection, message);
            });
        });
    }


    private static void getMsg2(Collection<String> collection, String message) {
        if (!message.contains("startTime") || !message.contains("endTime")) {
            System.out.println(message);
            return;
        }

        String startTime = "";
        String endTime = "";
        String createAt = "";
        String code = "";
        String storeId = "";
        String stationId = "";
        Matcher matcherStart = patternStart.matcher(message);
        if (matcherStart.find()) {
            startTime = matcherStart.group();
        }

        Matcher matcherEnd = patternEnd.matcher(message);
        if (matcherEnd.find()) {
            endTime = matcherEnd.group();
        }

        Matcher matcherCreateAt = patternCreateAt.matcher(message);
        if (matcherCreateAt.find()) {
            createAt = matcherCreateAt.group();
        }

        Matcher matcherCode = patternCode.matcher(message);
        if (matcherCode.find()) {
            code = matcherCode.group();
        }

        Matcher matcherStoreId = patternStoreId.matcher(message);
        if (matcherStoreId.find()) {
            storeId = matcherStoreId.group();
        }

        Matcher matcherStationId = patternStationId.matcher(message);
        if (matcherStationId.find()) {
            stationId = matcherStationId.group();
        }

        long executionTime = Long.parseLong(endTime) - Long.parseLong(startTime);
        if (executionTime > 0) {
            StringBuilder builder = new StringBuilder(48);
            builder.append(StringUtils.leftPad(Long.toString(executionTime), 6, ' ')).append(",\t");
            builder.append(createAt).append(",\t");
            builder.append(code).append(",\t");
            builder.append(StringUtils.leftPad(storeId, 5, ' ')).append(",\t");
            builder.append(stationId).append(",\t");
            builder.append(integer.getAndIncrement());
            collection.add(builder.toString());
        }
    }
}
