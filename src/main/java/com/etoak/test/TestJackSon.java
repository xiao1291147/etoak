package com.etoak.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etoak.po.Cat;
import com.etoak.po.Zoo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;

/**
 * TestJackSon
 *
 * @author xiao1
 * @date 2018/1/1
 */
public class TestJackSon {

    public static void main(String[] args) {
        Cat tom = new Cat();
        tom.setName("Tom");
        Zoo zoo = new Zoo();
        zoo.setCats(ImmutableList.of(tom));
        String jsonString = JSON.toJSONString(zoo);
        System.out.println(jsonString);
        Zoo readZoo = underLineJsonStrToCamelObj(jsonString, Zoo.class);
        System.out.println(readZoo);

        JSONObject jsonObject = new JSONObject();
        JSONObject nameJson = new JSONObject();
        nameJson.put("name", tom.getName());

        jsonObject.put("cats", ImmutableList.of(nameJson));
        System.out.println(jsonObject.toJSONString());
        Assert.assertEquals(jsonString, jsonObject.toJSONString());

//      JackSon读取对象，赋值JSON不要toString
        jsonObject.clear();
        jsonObject.put("cats", ImmutableList.of(nameJson.toJSONString()));
        System.out.println(jsonObject.toJSONString());
        Assert.assertEquals(jsonString, jsonObject.toJSONString());
    }

    private static <T> T underLineJsonStrToCamelObj(String jsonStr, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
