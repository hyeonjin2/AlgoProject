package com.year2023.month10.test1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main4 {

	static int N;
	static int[][] map;
	static List<Point> persons;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		persons = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			persons.add(new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
		}
		int cnt = 0;
		visited = new boolean[N][N];
		for (Point p : persons) {
			if (!visited[p.x][p.y]) {
				dfs(p.x, p.y);
				cnt++;
			}
		}
		int result = cnt == 1 ? 1 : 0;
		System.out.println(result);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void dfs(int x, int y) {
		visited[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (!inRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 0)
				continue;
			dfs(nx, ny);
		}
	}

	private static boolean inRange(int nx, int ny) {
		return (nx >= 0 && nx < N && ny >= 0 && ny < N);
	}

}
