package com.year2022.day1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 센서
public class BaekJoon2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sensors = new int[N];

        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        // 정렬
        Arrays.sort(sensors);
        // 센서들 사이의 거리 차이를 담을 배열
        Integer[] diff = new Integer[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = sensors[i + 1] - sensors[i];
        }
        // 센서들 사이 거리 내림차순 정렬
        Arrays.sort(diff, Collections.reverseOrder());

        // diff 배열 k-1 ~ 마지막까지 합
        // 첫번째
        int sum = 0;
        for (int i = K - 1; i < N - 1; i++) {
            sum += diff[i];
        }
        System.out.println(sum);
    }
}
