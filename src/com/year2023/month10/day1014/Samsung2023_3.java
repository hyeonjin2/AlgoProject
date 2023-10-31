package com.year2023.month10.day1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 2023 상반기 오후 1번
public class Samsung2023_3 {

	static int N, M, K, result;
	static int[][] map;
	static List<Person> persons;
	static Person end;

	static class Point {
		int x, y;
		int diff;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getDiff() {
			int diffX = Math.abs(end.x - this.x);
			int diffY = Math.abs(end.y - this.y);
			this.diff = Math.min(diffX, diffY);

			return this.diff;
		}

	}

	static class Person {
		int x, y;
		int cnt;

		public Person(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		public void go(int x, int y) {
			this.x = x;
			this.y = y;
			cnt++;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		persons = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			persons.add(new Person(x, y, 0));
		}

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;

		end = new Person(x, y, 0);

		print(map);
		// 게임 시작 후 K초가 지났거나,
		// 모든 참가자가 미로를 탈출했을 때
		while (K-- > 0) {
			if (exitAll())
				break;
			simulation();
		}
		// 모든 참가자들의 이동 거리 합과 출구 좌표 출력

	}

	// 모든 참가자가 탈출했는지 여부
	private static boolean exitAll() {
		return persons.isEmpty();
	}

	private static void simulation() {
		// 참가자 이동
		move();

		// 회전할 배열의 크기와 포함되는 사람 구하기
		int size = N; // 회전할 배열의 크기
		List<Point> points = new ArrayList<>();
		for (Person p : persons) {
			Point temp = new Point(p.x, p.y);
			int diff = temp.getDiff();
			if (size >= diff) {
				size = diff;
				for (int i = points.size() - 1; i >= 0; i--) {
					Point point = points.get(i);
					if (size < point.diff) {
						points.remove(point);
					}
				}
				points.add(temp);
			}
		}
		// 좌상단 구하기
		Point s = getStart(points, size);
		Point e = new Point(s.x + size, s.y + size);
		// 미로 회전
		rotation(s, e, size);

	}

	// 이동 우선순위 : 상,하
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 모든 참가자 한 칸씩 이동
	private static void move() {
		for (int i = persons.size() - 1; i >= 0; i--) {
			Person p = persons.get(i);
			int min = 2 * N + 1;
			int x = 0;
			int y = 0;
			List<int[]> list = new ArrayList<>();
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if (!inRange(nx, ny))
					continue;
				if (getDistance(x, y) <= min) {
					for (int j = list.size() - 1; j >= 0; j--) {
						int[] ind = list.get(j);
						if (getDistance(ind[0], ind[1]) > min)
							list.remove(j);
					}
					list.add(new int[] { x, y });
				}
			}
			// 배열의 우선순위가 상하좌우이므로 가장 앞에 있는 좌표가 우선순위가 높은 이동
			int[] next = list.get(0);
			// 벽이 아니어서 움직일 수 있다면 이동
			if (map[next[0]][next[1]] == 0) {
				p.go(next[0], next[1]);
				// 만약 출구에 도달했다면 cnt 답에 더하기
				if (p.x == end.x && p.y == end.y) {
					result += p.cnt;
					persons.remove(p);
				}
			}
		}

	}

	// 상, 우, 하, 좌
	static int[] dxR = { -1, 0, 1, 0 };
	static int[] dyR = { 0, 1, 0, -1 };

	// 배열 회전
	private static void rotation(Point s, Point e, int size) {
		// 회전 할 배열의 껍데기 수
		int cnt = size / 2;
		while (cnt-- > 0) {
			// 좌하단부터 배열에 넣기
			Point start = new Point(e.x, s.y);
			
		}
	}

	private static Point getStart(List<Point> points, int size) {
		List<Point> cand = new ArrayList<>();
		for (Point p : points) {
			// 출구와 사람이 같은 행에 있을 경우
			if (p.x == end.x) {
				// size 만큼 위 또는 아래에 배열이 있는 경우
				int y = Math.min(p.y, end.y);
				if (p.x - size > 0) {
					cand.add(new Point(p.x - size, y));
				} else {
					cand.add(new Point(0, y));
				}

			}
			// 출구와 사람이 같은 열에 있을 경우
			else if (p.y == end.y) {
				// size 만큼 왼쪽 또는 오른쪽으로 배열이 있는 경우
				int x = Math.min(p.x, end.x);
				if (p.y - size > 0) {
					cand.add(new Point(x, p.y - size));
				} else {
					cand.add(new Point(x, 0));
				}
			}
			// 그 외의 경우
			else {
				int diffX = Math.abs(p.x - end.x);
				int diffY = Math.abs(p.y - end.y);

				// size를 결정지은게 x의 차이라면
				if (diffX == size) {
					// x가 작은 값이 좌상단의 x값이 된다.
					int x = Math.min(p.x, end.x);
					int y = 0;
					// y값은 y가 더 큰 값에서 size만큼 뺀 값
					if (p.y > end.y) {
						y = (end.y - size > 0) ? end.y - size : 0;
					} else {
						y = (p.y - size > 0) ? p.y - size : 0;

					}
					cand.add(new Point(x, y));
				}
				if (diffY == size) {
					// y가 작은 값이 좌상단의 y값이 된다.
					int y = Math.min(p.y, end.y);
					int x = 0;
					// y값은 y가 더 큰 값에서 size만큼 뺀 값
					if (p.x > end.x) {
						x = (end.x - size > 0) ? end.x - size : 0;
					} else {
						x = (p.x - size > 0) ? p.x - size : 0;

					}
					cand.add(new Point(x, y));
				}
			}
		}
		cand.sort((e1, e2) -> {
			if (e1.x == e2.x)
				return e1.y - e2.y;
			return e1.x - e2.y;
		});

		return cand.get(0);
	}

	private static int getDistance(int x, int y) {
		return Math.abs(end.x - x) + Math.abs(end.y - y);
	}

	private static boolean inRange(int nx, int ny) {
		return (nx >= 0 && nx < N && ny >= 0 && ny < N);
	}

	private static void print(int[][] matrix) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
