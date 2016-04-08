package ru.kpfu.itis.utils;

import org.apache.commons.math3.random.RandomDataGenerator;

import java.text.NumberFormat;

public class RandomUtil {

    private NumberFormat numberFormat = NumberFormat.getInstance();

    public RandomUtil() {

        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
    }

    public int nextInt(int max) {
        RandomDataGenerator randomData = new RandomDataGenerator();
        randomData.reSeedSecure(randomData.nextLong(Long.MIN_VALUE, Long.MAX_VALUE));
        return randomData.nextSecureInt(0, max - 1);
    }

    public double nextDouble(double max) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        randomDataGenerator.reSeedSecure(randomDataGenerator.nextLong(Long.MIN_VALUE, Long.MAX_VALUE));
        double digit = randomDataGenerator.nextSecureInt(1, (int) max) + Math.random();
        return Double.parseDouble(numberFormat.format(digit).replace(",","."));
    }
}
