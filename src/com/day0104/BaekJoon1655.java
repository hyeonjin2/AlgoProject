package com.day0104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 가운데를 말해요
public class BaekJoon1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        int[] parents = new int[N];
        // subset
        for (int i = 0; i < N; i++) {
            parents[i] = nums[i];
        }

        int ind = 0;
        for (int i = 1; i < N; i++) {
            if (nums[ind] > nums[i]) {
//                parents[ind]
            }
        }
    }
}
