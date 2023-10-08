package com.year2023.month10.day1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.awt.Point;

// 파핑파핑 지뢰찾기
public class SWEA1868 {

	static int N, answer;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			answer = 0;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					if (str.charAt(j) == '.')
						map[i][j] = -1;
					else
						map[i][j] = -2;
				}
			}
			solve();
			System.out.println("#" + tc + " " + answer);
		}
	}

	static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 0, -1, 1, 1, -1, -1, 1 };

	private static void solve() {
		// 지뢰가 0이여서 클릭했을 때 연속적으로 땅이 넓어지는 경우
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != -1)
					continue;
				if (isZero(i, j)) {
					click(i, j);
					answer++;
				}
			}
		}
		// 연속적으로 땅이 넓어지지 않아 하나씩 눌러줘야하는 경우
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1)
					answer++;
			}
		}
	}

	private static void click(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		map[x][y] = 0;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			map[cur.x][cur.y] = 0;
			for (int d = 0; d < 8; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != -1)
					continue;
				if (isZero(nx, ny))
					queue.offer(new Point(nx, ny));
				map[nx][ny] = 0;
			}
		}
	}

	private static boolean isZero(int x, int y) {
		for (int d = 0; d < 8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			if (map[nx][ny] == -2)
				return false;
		}
		return true;
	}

}
