package com.github.soursop.matrix.operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static String asFormat(String format, Object ... objects) {
        return String.format(format, objects);
    }

    public static double[] as(double ... doubles) {
        return doubles;
    }

    public static List<Double> asList(double ... doubles) {
        List<Double> list = new ArrayList<>();
        for (double v : doubles) {
            list.add(v);
        }
        return list;
    }
}
