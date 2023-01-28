package com.year2023.day0124;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 가장높은탑쌓기
public class BaekJoon2655 {

  static class Brick implements Comparable<Brick> {
    int id, area, height, weight;

    public Brick(int id, int area, int height, int weight) {
      this.id = id;
      this.area = area;
      this.height = height;
      this.weight = weight;
    }

    @Override
    public int compareTo(Brick o) {
      return this.weight - o.weight;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    Brick[] bricks = new Brick[N + 1];
    StringTokenizer st;
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int area = Integer.parseInt(st.nextToken());
      int height = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      bricks[i] = new Brick(i, area, height, weight);
    }
    bricks[0] = new Brick(0, 0, 0, 0);

    // 벽돌 무게로 오름차순 정렬
    Arrays.sort(bricks);

    // d[i]: i번째 벽돌이 가장 아래에 있을 때 탑의 최대 높이
    int[] d = new int[N + 1];
    int maxHeight = -1;
    // 탑 최대 높이 구하기
    for (int i = 1; i <= N; i++) {
      for (int j = 0; j < i; j++) {
        // i의 넓이가 j보다 더 크면 j위에 i 쌓기
        if (bricks[i].area > bricks[j].area) {
          d[i] = Math.max(d[i], d[j] + bricks[i].height);
        }
      }
      maxHeight = Math.max(maxHeight, d[i]);
    }

    // 사용된 벽돌 구하기
    Stack<Integer> stack = new Stack<>();
    int index = N;
    while (index > 0) {
      if (maxHeight == d[index]) {
        stack.add(bricks[index].id);
        maxHeight -= bricks[index].height;
      }
      index--;
    }
    System.out.println(stack.size());
    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }
  }
}
