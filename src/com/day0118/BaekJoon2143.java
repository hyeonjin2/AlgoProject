package com.day0118;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon2143 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long T = Long.parseLong(br.readLine());

    int n = Integer.parseInt(br.readLine());
    long[] arrA = new long[n]; // A배열

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      long a = Long.parseLong(st.nextToken());
      if (i == 0) {
        arrA[i] = a;
        continue;
      }
      arrA[i] = arrA[i - 1] + a;
    }

    int m = Integer.parseInt(br.readLine());
    long[] arrB = new long[m]; // B배열

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; i++) {
      long b = Long.parseLong(st.nextToken());
      if (i == 0) {
        arrB[i] = b;
        continue;
      }
      arrB[i] += arrB[i - 1] + b;
    }

    int sizeA = n * (n + 1) / 2;
    int sizeB = m * (m + 1) / 2;

    long[] sumA = new long[sizeA]; // A배열의 모든 부분합
    int index = 0;
    for (int start = 0; start < n; start++) {
      for (int end = start; end < n; end++) {
        long a = arrA[end];
        if (start > 0) {
          a -= arrA[start - 1];
        }
        sumA[index++] = a;
      }
    }
    long[] sumB = new long[sizeB];
    index = 0;
    for (int start = 0; start < m; start++) {
      for (int end = start; end < m; end++) {
        long b = arrB[end];
        if (start > 0) {
          b -= arrB[start - 1];
        }
        sumB[index++] = b;
      }
    }
    // 투 포인터로 A부분합 + B부분합==T 인 경우의 수 찾기
    Arrays.sort(sumA);
    Arrays.sort(sumB);
    int left = 0;
    int right = sizeB - 1;
    long ans = 0;
    while (left < sizeA && right >= 0) {
      long a = sumA[left];
      long b = sumB[right];
      long sum = a + b;
      // 각 중복되는 원소의 크기를 구해 카운트 해주기
      if (sum == T) {
        long countA = 0;
        long countB = 0;
        while (left < sizeA && a == sumA[left]) {
          left++;
          countA++;
        }
        while (right >= 0 && b == sumB[right]) {
          right--;
          countB++;
        }
        ans += countA * countB;
      }
      // T에 가까운 부분합 구하기
      if (sum > T) {
        right--;
      } else if (sum < T) {
        left++;
      }
    }
    System.out.println(ans);
  }
}
