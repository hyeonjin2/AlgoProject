package com.year2023.month10.day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.awt.Point;

//2022 하반기 오후 1번
public class Samsung2022_7 {

	static int N, M;
	static int[][] map;
	static Point[] targets;
	static Queue<Person> persons;
	static boolean[][][] visited;
	static int[] cnt;

	// 이동 우선순위 : 상, 좌, 우, 하
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	static class Person {
		int id;
		int x, y;

		public Person(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		targets = new Point[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			targets[i] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}

		int result = solution();
		System.out.println(result);
//		print(map);
	}

	// map[i][j] = -1 : 이동할 수 없는 칸 (베이스캠프에 도착했거나, 편의점에 도착한 경우)
	private static int solution() {
		persons = new ArrayDeque<>();
		visited = new boolean[M + 1][N][N];
		cnt = new int[M + 1];
		// 사람 모두 베이스 캠프로 이동
		for (int i = 1; i <= M; i++) {
			move(i);
			Point start = findCamp(i);
			persons.offer(new Person(i, start.x, start.y));
			// 더이상 베이스 캠프를 지나갈 수 없음
			map[start.x][start.y] = -1;
			visited[i][start.x][start.y] = true;
//			print(map);
		}
		int t = M + 1;
		while (!isFull()) {
			move(t++);
		}
		int result = 0;
		for (int i = 1; i <= M; i++) {
			if (result < cnt[i])
				result = cnt[i];
		}
//		System.out.println(Arrays.toString(cnt));
		return result;
	}

	private static void move(int t) {
		int size = persons.size();
//		System.out.println("queue : " + persons);
//		System.out.println("========== " + t + "분 ==========");
		List<Point> list = new ArrayList<Point>();
		for (int i = 0; i < size; i++) {
			Person cur = persons.poll();
			Point target = targets[cur.id];
			if (cnt[cur.id] > 0)
				continue;
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (!inRange(nx, ny) || visited[cur.id][nx][ny] || map[nx][ny] == -1)
					continue;
				if (target.x == nx && target.y == ny) {
					list.add(target);
					cnt[cur.id] = t;
				} else {
					visited[cur.id][nx][ny] = true;
					persons.offer(new Person(cur.id, nx, ny));
				}
			}
		}
		for (Point p : list) {
			map[p.x][p.y] = -1;
		}
	}

	static boolean inRange(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			return false;
		return true;
	}

	// t번째 사람이 갈 수 있는 베이스캠프를 찾는 메소드
	static Point findCamp(int idx) {
		Point target = targets[idx];
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		queue.offer(target);
		visited[target.x][target.y] = true;

		List<Point> list = new ArrayList<>();
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point cur = queue.poll();
				if (list.size() > 0)
					break;
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					// 배열의 범위를 벗어나거나 이미 방문한 곳이거나 지나갈 수 없는 곳이면 continue
					if (!inRange(nx, ny) || visited[nx][ny] || map[nx][ny] == -1)
						continue;
					if (map[nx][ny] == 1) {
						list.add(new Point(nx, ny));
					} else {
						visited[nx][ny] = true;
						queue.offer(new Point(nx, ny));
					}
				}
			}
		}

		list.sort((e1, e2) -> {
			if (e1.x == e2.x)
				return e1.y - e2.y;
			return e1.x - e2.x;
		});

		return list.get(0);
	}

	// 사람들이 모두 target에 도착해있는지 확인하는 메소드
	static boolean isFull() {
		for (int i = 1; i <= M; i++) {
			if (map[targets[i].x][targets[i].y] != -1)
				return false;
		}
		return true;
	}

	static void print(int[][] matrix) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

	}

}
