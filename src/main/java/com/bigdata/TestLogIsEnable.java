package com.bigdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by GP39 on 2016/8/18.
 */
public class TestLogIsEnable {
    Logger log = LoggerFactory.getLogger(getClass());

    public static void main(String[] args){
        TestLogIsEnable b = new TestLogIsEnable();
        b.testLog();
    }

    public void  testLog(){
        log.info("hello log");
    }
}
