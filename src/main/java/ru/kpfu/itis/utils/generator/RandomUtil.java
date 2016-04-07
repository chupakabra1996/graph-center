package ru.kpfu.itis.utils.generator;

import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomUtil {

    private static final int DEFAULT_MAX_DATA = 23;

    //the max size of generated int
    private int maxData;

    private RandomDataGenerator randomData;

    public RandomUtil() {
        this.maxData = DEFAULT_MAX_DATA;
    }

    public RandomUtil(int maxData) {
        randomData = new RandomDataGenerator();
        this.maxData = maxData;
    }

    public int nextInt(){
        randomData = new RandomDataGenerator();
        randomData.reSeedSecure(randomData.nextLong(Long.MIN_VALUE,Long.MAX_VALUE));
        return randomData.nextSecureInt(0, maxData);
    }

}
