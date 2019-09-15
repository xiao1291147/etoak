package com.demo.pattern.factory;

/**
 * 发型工厂
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class HairFactory {

    /**
     * 根据类型来创建发型
     *
     * @param key
     * @return
     */
    public HairInterface getHair(String key) {
        if ("left".equals(key)) {
            return new LeftHair();
        } else if ("right".equals(key)) {
            return new RightHair();
        }

        return null;
    }

    /**
     * 根据类的名称来生产对象
     *
     * @param className
     * @return
     */
    public HairInterface getHairByClass(String className) {
        try {
            return (HairInterface) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
