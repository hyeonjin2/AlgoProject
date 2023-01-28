package com.year2023.month01.day0128;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// RGB거리 2
public class BaekJoon17404 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[][] house = new int[N][3];
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      house[i][0] = Integer.parseInt(st.nextToken());
      house[i][1] = Integer.parseInt(st.nextToken());
      house[i][2] = Integer.parseInt(st.nextToken());
    }
    int[][] d = new int[N][3];
    final int INF = 1000 * 1000;
    int ans = INF;
    // 1번집의 색을 RGB를 정해서 d배열을 구한다.
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < 3; i++) {
        if (i == k) {
          d[0][i] = house[0][i];
        } else {
          d[0][i] = INF;
        }
      }
      for (int i = 1; i < N; i++) {
        d[i][0] = Math.min(d[i - 1][1], d[i - 1][2]) + house[i][0];
        d[i][1] = Math.min(d[i - 1][0], d[i - 1][2]) + house[i][1];
        d[i][2] = Math.min(d[i - 1][0], d[i - 1][1]) + house[i][2];
      }
      // 1번집과 다른 색인 최소 비용 구하기
      for (int i = 0; i < 3; i++) {
        if (i != k) {
          ans = Math.min(ans, d[N - 1][i]);
        }
      }
    }

    System.out.println(ans);
  }

}
