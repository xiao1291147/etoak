package com.demo.guice.printlndemo;

import com.demo.guice.MyApplet;

/**
 * PrintLineApplet
 * @author xiao1
 * @date 2019/2/17
 */
public class PrintLineApplet implements MyApplet {

    @Override
    public void run() {
        System.out.println("printLineApplet");
    }
}
