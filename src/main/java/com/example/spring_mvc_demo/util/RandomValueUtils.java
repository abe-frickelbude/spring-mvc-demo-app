package com.example.spring_mvc_demo.util;

import java.util.List;
import org.apache.commons.lang3.RandomUtils;

/**
 * 
 * @author Ibragim Kuliev
 *
 */
public final class RandomValueUtils {

    private RandomValueUtils() {
        // disabled default constructor
    }

    /**
     * 
     * Can be used to pick a random value from an array or anything that is [internally] represented as an array, e.g.
     * an enum.
     *
     */
    public static <T> T pickRandomValue(final T values[]) {
        return values[RandomUtils.nextInt(0, values.length)];
    }

    public static <T> T pickRandomValue(final List<T> values) {
        return values.get(RandomUtils.nextInt(0, values.size()));
    }
}
