package com.demo.pattern.adapter;

/**
 * 笔记本
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class NoteBook {

    private final ThreePlugIf plug;

    public NoteBook(ThreePlugIf plug) {
        this.plug = plug;
    }

    /**
     * 使用插座充电
     */
    public void charge() {
        plug.powerWithThree();
    }

    public static void main(String[] args) {
        GBTwoPlug two = new GBTwoPlug();
        ThreePlugIf three = new TwoPlugAdapter(two);
        NoteBook noteBook = new NoteBook(three);
        noteBook.charge();

        three = new TwoPlugAdapterExtends();
        noteBook = new NoteBook(three);
        noteBook.charge();
    }
}
