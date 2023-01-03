package com.day0103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가장 긴 바이토닉 부분 수열
public class BaekJoon11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int[] arr = new int[N];
        int[] arrR = new int[N];
        int[] result = new int[N];
        int ans = 0;

        Arrays.fill(arr, 1);
        Arrays.fill(arrR, 1);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (numbers[i] < numbers[j]) {
                    arr[j] = Math.max(arr[j], arr[i] + 1);
                }
                if (numbers[N - i - 1] < numbers[N - j - 1]) {
                    arrR[N - j - 1] = Math.max(arrR[N - j - 1], arrR[N - i - 1] + 1);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            result[i] = arr[i] + arrR[i] - 1;
            ans = Math.max(ans, result[i]);
        }
        System.out.println(ans);
    }
}
