package com.besmart.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("application.properties")
public class Configurations {

    @Value("${delay.in.sec}")
    protected long delayInSec;

    @Value("${init.delay.in.sec}")
    protected long initDelayInSec;

    @Value("${fixed.delay.in.milliseconds}")
    protected int fixedDelayInMilliseconds;

    @Value("${number.of.digit.after.point}")
    protected String numberOfDigitAfterPoint;

    @Value("${storage.mode}")
    protected String storageMode;

    public long getDelayInSec() {
        return delayInSec;
    }

    public long getInitDelayInSec() {
        return initDelayInSec;
    }

    public int getFixedDelayInMilliseconds() {
        return fixedDelayInMilliseconds;
    }

    public String getNumberOfDigitAfterPoint() {
        return numberOfDigitAfterPoint;
    }

    public String getStorageMode() {
        return storageMode;
    }
}