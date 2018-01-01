package com.etoak.po;

import java.util.List;

/**
 * Zoo
 *
 * @author xiao1
 * @date 2018/1/1
 */
public class Zoo {

    private List<Cat> cats;

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Zoo{");
        builder.append("cats=").append(cats);
        builder.append('}');
        return builder.toString();
    }
}
