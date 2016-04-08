package ru.kpfu.itis.utils;

import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomUtil {

    public RandomUtil() {}

    public int nextInt(int max) {
        RandomDataGenerator randomData = new RandomDataGenerator();
        randomData.reSeedSecure(randomData.nextLong(Long.MIN_VALUE, Long.MAX_VALUE));
        return randomData.nextSecureInt(0, max-1);
    }

    public double nextDouble(double max) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        randomDataGenerator.reSeedSecure(randomDataGenerator.nextLong(Long.MIN_VALUE, Long.MAX_VALUE));
        return randomDataGenerator.nextSecureInt(1, (int) max);
    }
}
