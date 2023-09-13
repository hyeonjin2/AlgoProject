package com.year2023.month08.day0823;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, result;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();
		System.out.println(result);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Point(0, 0));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			if (x == N - 1 && y == M - 1) {
				result = 1;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 0)
					continue;
				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}

}
