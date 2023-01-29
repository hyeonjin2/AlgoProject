package com.year2023.month01.day0129;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2638 치즈
public class BaekJoon2638 {

  static int N, M, time, totalCnt;
  static int[][] map;
  static boolean[][] visited;
  static List<Point> cheese;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    cheese = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 1) {
          totalCnt++;
          cheese.add(new Point(i, j));
        }
      }
    }
//    print(map);
    while (totalCnt != 0) {
      time++;
      // 외부공기 판별하기 -> 2
      visited = new boolean[N][M];
      setAir();
      // 치즈 녹이기
      melting();
    }
    System.out.println(time);
  }


  private static final int[] dx = {-1, 1, 0, 0};
  private static final int[] dy = {0, 0, -1, 1};

  private static void setAir() {
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(0, 0));
    map[0][0] = 2;
    while (!queue.isEmpty()) {
      Point cur = queue.poll();
      int x = cur.x;
      int y = cur.y;

      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M ||
          visited[nx][ny] || map[nx][ny] == 1)
          continue;

        map[nx][ny] = 2;
        visited[nx][ny] = true;
        queue.offer(new Point(nx, ny));
      }
    }
  }

  private static void melting() {
    for (int i = 0; i < cheese.size(); i++) {
      Point cur = cheese.get(i);
      int x = cur.x;
      int y = cur.y;
      int cnt = 0; // 외부 공기와 닿아있는 칸의 수

      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (map[nx][ny] == 2)
          cnt++;
      }
      if (cnt >= 2) {
        map[x][y] = 0;
        totalCnt--;
        cheese.remove(i);
        i--;
      }
    }
  }

  private static void print(int[][] arr) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        sb.append(arr[i][j]).append(" ");
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }
}
