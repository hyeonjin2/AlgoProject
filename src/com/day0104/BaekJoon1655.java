package com.day0104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 가운데를 말해요
public class BaekJoon1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = Integer.parseInt(br.readLine());

        for (int i = 1; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            if (ans > temp) {
                ans = temp;
            }
            // ans <= temp
            else {
                if (i % 2 == 1) {
//                    ans = ;
                } else {

                }
            }
        }
    }
}
