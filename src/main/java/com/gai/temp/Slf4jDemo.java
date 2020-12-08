package com.gai.temp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jDemo {
    private Logger logger = LoggerFactory.getLogger(Slf4jDemo.class);

    public static void main(String[] args) {
        Slf4jDemo slf4jDemo = new Slf4jDemo();
        slf4jDemo.logger.info("info 输出");
        slf4jDemo.logger.debug("debug 输出");
    }
}
