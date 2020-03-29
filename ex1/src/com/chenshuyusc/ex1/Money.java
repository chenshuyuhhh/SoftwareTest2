package com.chenshuyusc.ex1;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * There is one 50 yuan, one 20 yuan, one 10 yuan, two 5 yuan bills
 * and three 1 yuan coins in your pocket.
 * Write a program to find out whether you can take out a given number (x) yuan.
 */
public class Money {

    private static int[] money = {1, 1, 1, 5, 5, 10, 20, 50};// the array which save all money

    /**
     * @param num given number (x) yuan.
     * @return whether can take out a given number (x) yuan.
     */
    public boolean findMoney(int num) {
        boolean result = false; // recored result

        if (num > 93) return false;  // exceed max money
        else if (num >= 50) {
            // must contain 50
            num = num - 50;
            result = findMoney(num);// num <= 43
        } else if (num > 43) { // 1+1+1+5+5+10+20=43
            return false;
        } else if (num > 23) { // 1+1+1+5+5+10=23
            // must contain 20
            num = num - 20;
            result = findMoney(num); // num <= 23
            // when num <= 23
            // whether 20 is used or not is not important
            // because when num <= 23,only when num >= 20, 20 has chance to be used
            // but at this time 20 can be replaced by 10+5+5
        } else {
            // num<=23
            // at this time only the last number of num is important
            // because 10+5+5=20, 10=10
            num = num % 10; // use this method to get the last number of num
            // only has three 1, so num cannot be 4 or 9
            return num != 4 && num != 9;
        }
        return result;
    }

    //To find how many case
    public static Set<Integer> allCase() {
        // use class CopyOnWriteArraySet to modify set
        Set<Integer> possible = new CopyOnWriteArraySet<>();

        // initial set
        possible.add(0);
        for (int num : money) {
            for (Object aPossible : possible) {
                // not has
                int temp = (int) aPossible;
                // has
                possible.add(temp + num);
            }
        }

        // possible: 0 1 2 3 5 6 7 8 10 11 12 13 15 16 17 18 20 21 22 23
        // 25 26 27 28 30 31 32 33 35 36 37 38 40 41 42
        // 43 50 51 52 53 55 56 57 58 60 61 62 63 65 66 67 68 70 71 72 73 75 76 77 78 80 81 82 83 85 86 87 88 90 91 92 93
        return possible;
    }
}
