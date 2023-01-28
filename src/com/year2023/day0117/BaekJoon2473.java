package com.year2023.day0117;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 세 용액
public class BaekJoon2473 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    long[] nums = new long[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(nums);
    long min = Long.MAX_VALUE;
    long[] ans = new long[3];
    for (int i = 0; i < n; i++) {
      int start = i + 1;
      int end = n - 1;
      while (start < end) {
        long sum = nums[i] + nums[start] + nums[end];
        long absSum = Math.abs(sum);
        if (absSum < min) {
          ans[0] = nums[i];
          ans[1] = nums[start];
          ans[2] = nums[end];
          min = absSum;
        }
        if (sum > 0) {
          end--;
        } else {
          start++;
        }
      }
    }
    Arrays.sort(ans);
    for (long a : ans) {
      System.out.print(a + " ");
    }
  }
}
