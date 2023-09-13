package com.year2023.month07.day0710;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		Point a = new Point();
		Point b = new Point(0, 0);

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					a = new Point(i, j);
				if (map[i][j] == 3)
					b = new Point(i, j);

			}
		}

//		print(map);
		int aSize = bfs(a.x, a.y);
		int bSize = bfs(b.x, b.y);
		System.out.println(aSize + " " + bSize);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static int bfs(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited = new boolean[N][N];
		visited[x][y] = true;
		int cnt = 1;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int r = cur.x;
			int c = cur.y;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] != 1)
					continue;

				queue.offer(new Point(nr, nc));
				visited[nr][nc] = true;
				cnt++;
			}
		}
		return cnt;
	}

	/*
	private static void print(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	*/
}
