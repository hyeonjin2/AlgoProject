package com.year2023.month01.day0129;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 텀 프로젝트
public class BaekJoon9466 {
  static int N, count;
  static int[] student;
  static boolean[] visited, finished;

  public static void main(String[] args) throws Exception {
    // 3->3, 1->2->3->1 이런 경우만 여러명이 팀을 이룰 수 있다. -> 학생들간의 싸이클이 존재해야함
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int tc = 0; tc < T; tc++) {
      N = Integer.parseInt(br.readLine());
      student = new int[N + 1]; // 학생들이 선택한 다른 학생 정보
      visited = new boolean[N + 1]; // 노드의 방문체크
      finished = new boolean[N + 1]; // 싸이클 완성여부

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++) {
        student[i] = Integer.parseInt(st.nextToken());
      }
      // 싸이클 확인하기
      count = 0;
      for (int i = 1; i <= N; i++) {
        if (!visited[i])
          dfs(i);
      }
      System.out.println(N - count);
    }
  }

  private static void dfs(int cur) {
    visited[cur] = true;
    int next = student[cur];

    // 방문하지 않았으면 다음 노드 방문하러가기
    if (!visited[next]) {
      dfs(next);
    }
    // 이미 방문했다면 싸이클인지 아닌지 확인
    else {
      // 싸이클이 완성된 경우 -> 싸이클에 포함된 학생 수 세기
      if (!finished[next]) {
        count++;
        int temp = next;

        while (temp != cur) {
          temp = student[temp];
          count++;
        }
      }
    }
    // 할 수 있는 모든 작업이 끝난 상태
    finished[cur] = true;
  }
}
