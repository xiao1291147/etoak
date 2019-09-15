package com.demo.pattern.factory;

/**
 * 人物的实现接口
 *
 * @author xiaol
 * @date 2019/9/14
 */
public interface PersonFactory {

    Boy getBoy();

    Girl getGirl();
}
