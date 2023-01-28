package com.year2023.month01.day0124;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분합
public class BaekJoon1806 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    int[] nums = new int[N + 1]; // N크기로 하게 되면 ArrayIndexOutOfBoundsException이 발생하므로 안전하게 N+1크기로 정해준다.
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    int left = 0;
    int right = 0;
    int min = N + 1;
    int sum = 0;

    while (left <= N && right <= N) {
      // sum이 S이상일 때
      if (sum >= S && min > right - left) {
        min = right - left;
      }
      // S보다 작으면 right++해서 값 더하기
      if (sum < S) {
        sum += nums[right++];
      }
      // S보다 크면 left++해서 값 빼기
      else {
        sum -= nums[left++];
      }
    }
    if (min == N + 1)
      min = 0;
    System.out.println(min);
  }
}
