package ru.job4j.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageSlf4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void func(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }

    public static void func2(String[] args) {
        byte bytedata = 123;
        short shortData = 10000;
        int intData = -1;
        long longData = 10L;
        float floatData = 123.2133F;
        double doubleData = 2.32D;
        char charData = 'C';
        boolean booleanData = true;
        LOG.debug("Data: {} {} {} {} {} {} {} {}",
                bytedata, shortData, intData, longData, floatData, doubleData, charData, booleanData);
    }

    public static void main(String[] args) {
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
