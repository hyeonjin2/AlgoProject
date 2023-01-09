package com.day0109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 저울
public class BaekJoon2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[n];
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            sum += weight[i];
        }
        Arrays.sort(weight);
        int ans = sum - weight[n - 1] + 1;
        if (weight[n - 1] == 1) {
            ans = sum + 1;
        }
        System.out.println(ans);
    }
}
