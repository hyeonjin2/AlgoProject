package com.year2023.month01.day0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 부분 수열의 합2
public class BaekJoon1208 {

  static int[] arr;
  static int N, S;
  static List<Integer> leftList, rightList;


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    // 배열을 두 배열로 나눠 각 배열에서 모든 부분 수열의 합을 구함
    leftList = new ArrayList<>();
    rightList = new ArrayList<>();

    makeSum(0, 0, N / 2, leftList);
    makeSum(0, N / 2, N, rightList);

    // 두 리스트 정렬
    Collections.sort(leftList);
    Collections.sort(rightList);

    long cnt = calcCnt();
    if (S == 0) {
      cnt--;
    }
    System.out.println(cnt);
  }

  // 투 포인터를 이용해 부분 수열의 합이 s가 되는 경우 찾기
  private static long calcCnt() {
    int left = 0;
    int right = rightList.size() - 1;
    long cnt = 0;
    while (left < leftList.size() && right >= 0) {
      long sum = leftList.get(left) + rightList.get(right);
      // 합이 같은 경우 모두 계산하기
      if (sum == S) {
        long l = leftList.get(left);
        long cnt1 = 0;
        while (left < leftList.size() && leftList.get(left) == l) {
          left++;
          cnt1++;
        }

        long r = rightList.get(right);
        long cnt2 = 0;
        while (right >= 0 && rightList.get(right) == r) {
          right--;
          cnt2++;
        }
        cnt += cnt1 * cnt2;
      } else if (sum < S) {
        left++;
      } else {
        right--;
      }
    }
    return cnt;
  }

  // 부분 수열의 합 구하기
  private static void makeSum(int sum, int start, int end, List<Integer> list) {
    if (start == end) {
      list.add(sum);
      return;
    }
    // arr[start]가 부분 수열에 포함되지 않아 더해지지 않는 경우
    makeSum(sum, start + 1, end, list);
    // arr[start]가 부분 수열에 포함되어 더해지는 경우
    makeSum(sum + arr[start], start + 1, end, list);
  }
}
