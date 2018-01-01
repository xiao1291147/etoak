package com.etoak.po;

/**
 * Cat
 *
 * @author xiao1
 * @date 2018/1/1
 */
public class Cat {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Cat{");
        builder.append("name='").append(name).append('\'');
        builder.append('}');
        return builder.toString();
    }
}
