package com.example.spring_mvc_demo.service.component_generator.builder;

import com.example.spring_mvc_demo.util.RandomValueUtils;

public final class BuilderUtils {

    private static final Integer TOLERANCES[] = {
            1, 2, 5, 10
    };

    public static Integer pickRandomTolerance() {
        return RandomValueUtils.pickRandomValue(TOLERANCES);
    }
}
