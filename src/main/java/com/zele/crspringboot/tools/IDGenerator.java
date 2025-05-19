package com.zele.crspringboot.tools;

import java.util.Random;

public class IDGenerator {
    private static Random rand = new Random();
    public static Long generateCourseID() {
        return rand.nextLong(10);
    }
}
