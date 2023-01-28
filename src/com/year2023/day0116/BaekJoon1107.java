package com.year2023.day0116;

import java.util.Scanner;

// 리모컨
public class BaekJoon1107 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int target = sc.nextInt();
    int n = sc.nextInt();

    boolean[] broken = new boolean[10];
    for (int i = 0; i < n; i++) {
      int temp = sc.nextInt();
      broken[temp] = true;
    }
    // 1. 가장 가까운 수를 찾는다.
    int ans = Math.abs(target - 100); // 처음 채널은 100
    // 최댓값이 500000 -> 6자리수 중 최댓값 999999
    for (int i = 0; i <= 999999; i++) {
      String str = String.valueOf(i);
      int len = str.length();

      boolean possible = true; // 숫자를 만드는 것이 가능한지
      for (int j = 0; j < len; j++) {
        // 고장난 버튼의 수를 포함하면
        if (broken[str.charAt(j) - '0']) {
          possible = false; // 불가능
          break;
        }
      }
      // 가능한 경우
      if (possible) {
        int min = Math.abs(target - i) + len;
        ans = Math.min(ans, min);
      }
    }
    System.out.println(ans);
  }
}
