package com.group.unit;

import lombok.Getter;

@Getter
public class Dice {
    private int number; // 주사위 숫자
    private int[] countByNumberRange; // 주사위 숫자별 나온 횟수 카운크

    public Dice(int number) {
        this.number = number;
        this.countByNumberRange = new int[number];
    }

    public void run() {
        double result = Math.random() * number;
        countByNumberRange[(int) result]++;
    }

    public void print() {
        for (int i = 0; i < number; i++) {
            System.out.printf((i+1) + "은 %d번 나왔습니다.\n", countByNumberRange[i]);
        }
    }
}
