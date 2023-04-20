package me.sfclog.minions.util;

public class RandomUtil {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public static int getRandomNumber(double min, double max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
