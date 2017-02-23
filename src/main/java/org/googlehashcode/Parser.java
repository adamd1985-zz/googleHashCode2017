package org.googlehashcode;

import java.util.Arrays;

/**
 * Created by magiccrafter on 2/23/2017.
 */
public class Parser {

    public static Integer[] integerArray(String line) {
        return Arrays.stream(line.split(" ")).map(s -> Integer.valueOf(s)).toArray(Integer[]::new);
    }
}
