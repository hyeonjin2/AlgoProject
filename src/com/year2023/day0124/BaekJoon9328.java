package com.year2023.day0124;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


// 열쇠
public class BaekJoon9328 {
  static class Point implements Comparable<Point> {
    int x, y, cntKey;

    public Point(int x, int y, int cntKey) {
      this.x = x;
      this.y = y;
      this.cntKey = cntKey;
    }

    // 키를 많이 가지고 있을 때 기준으로 빠르게 탐색하기 위해
    @Override
    public int compareTo(Point o) {
      return o.cntKey - this.cntKey;
    }
  }

  static int N, M, cntKey, ans;
  static boolean[] hasKeys;
  static char[][] map;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      map = new char[N + 2][M + 2];
      // 외벽 만들기
      for (int i = 0; i <= N + 1; i++) {
        Arrays.fill(map[i], '.');
      }

      // 맵 정보 입력받기
      for (int i = 1; i <= N; i++) {
        String str = br.readLine();
        for (int j = 0; j < M; j++) {
          map[i][j + 1] = str.charAt(j);
        }
      }
      // 이미 가지고 있는 열쇠 정보 저장하기 -> boolean 배열에 저장
      String key = br.readLine();
      hasKeys = new boolean['z' - 'a' + 1];
      cntKey = 0;
      if (!key.equals("0")) {
        char[] keys = key.toCharArray();
        cntKey = keys.length;
        for (char k : keys) {
          hasKeys[k - 'a'] = true;
        }
      }

      // bfs 탐색
      ans = 0;
      goSteal();
      System.out.println(ans);
    }
  }

  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  private static void goSteal() {
    PriorityQueue<Point> queue = new PriorityQueue<>();
    boolean[][][] visited = new boolean['z' - 'a' + 2][N + 2][M + 2]; // 1개도 가지고 있지 않을 때를 포함
    queue.offer(new Point(0, 0, cntKey));
    visited[cntKey][0][0] = true;
    while (!queue.isEmpty()) {
      Point cur = queue.poll();
      int curKey = cur.cntKey;
      int x = cur.x;
      int y = cur.y;
      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        // 배열의 범위를 벗어나거나 이동할 수 없는 곳(*)인 경우 continue
        if (nx < 0 || nx >= N + 2 || ny < 0 || ny >= M + 2 || map[nx][ny] == '*' || visited[curKey][nx][ny])
          continue;
        // 빈 공간이면
        if (map[nx][ny] == '.') {
          // 빈 공간으로 만들고 이동하기
          go(nx, ny, curKey, visited, queue);
        }
        // 이미 가지고 있는 열쇠라면
        else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z' && hasKeys[map[nx][ny] - 'a']) {
          // 빈 공간으로 만들고 이동하기
          go(nx, ny, curKey, visited, queue);
        }
        // 가지고 있지 않은 열쇠라면
        else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z' && !hasKeys[map[nx][ny] - 'a']) {
          // 열쇠 줍기
          hasKeys[map[nx][ny] - 'a'] = true;
          curKey++;
          cntKey++;
          // 빈 공간으로 만들고 이동하기
          go(nx, ny, curKey, visited, queue);
        }
        // 문이라면
        else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
          int door = map[nx][ny] - 'A';
          // 문의 열쇠를 가지고 있다면
          if (hasKeys[door]) {
            // 빈 공간으로 만들고 이동하기
            go(nx, ny, curKey, visited, queue);
          }
        }
        // 문서라면
        else if (map[nx][ny] == '$') {
          ans++;
          // 빈 공간으로 만들고 이동하기
          go(nx, ny, curKey, visited, queue);
        }
      }
    }
  }

  private static void go(int nx, int ny, int curKey, boolean[][][] visited, PriorityQueue<Point> queue) {
    // 빈 공간으로 만들기
    map[nx][ny] = '.';
    // 이동하기
    visited[curKey][nx][ny] = true;
    queue.offer(new Point(nx, ny, curKey));
  }

}
