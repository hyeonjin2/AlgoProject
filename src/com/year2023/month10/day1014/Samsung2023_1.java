package com.year2023.month10.day1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 2023 상반기 오전 1번
public class Samsung2023_1 {

	final static int MAX_POWER = 5001;
	static int N, M, K;
	static int[][] map;
	static HashMap<Integer, Integer> lastAttack;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getIndex() {
			return this.x * M + this.y;
		}

		public int getSum() {
			return this.x + this.y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	static class Route {
		Point p;
		List<Point> routes = new ArrayList<>();

		public Route(Point p) {
			this.p = p;
		}

		public Route(Point p, List<Point> routes) {
			this.p = p;
			this.routes = routes;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		lastAttack = new HashMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				lastAttack.put(i * M + j, 0);
			}
		}
		for (int i = 1; i <= K; i++) {
			if (!isEnd()) {
				simulation(i);
//				System.out.println("==================" + i + "번째 공격 result =================");
//				print(map);
			}
		}
		System.out.println(result());
	}

	private static boolean isEnd() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0)
					cnt++;
			}
		}
		if (cnt == 1)
			return true;
		return false;
	}

	private static int result() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (result < map[i][j]) {
					result = map[i][j];
				}
			}
		}
		return result;
	}

	// 이동 우선순위 : 우 하 좌 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static boolean[][] isRelated;

	private static void simulation(int t) {
		isRelated = new boolean[N][M];
		// 1. 공격자 선정
		Point attacker = selectAttacker(t);
//		System.out.println("attacker : " + attacker);
//		print(map);
		// 2. 공격자의 공격
		// 2-0. 가장 강한 포탑 고르기
		Point attacked = selectPowerful(attacker);
//		System.out.println("attacked : " + attacked);
		// 2-1. 레이저 공격 시도
		if (!attackLaser(attacker, attacked)) {
			// 레이저 공격 실패하면 포탄 공격
			attackBomb(attacker, attacked);
//			System.out.println("attack using bomb");
		}
//		System.out.println("============== after attack =================");
//		print(map);

		// 3. 포탑 부서짐
		brokenTower();

		// 4. 포탑 정비
		setTower();

	}

	private static void setTower() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!isRelated[i][j] && map[i][j] != 0) {
					map[i][j]++;
				}
			}
		}
	}

	private static void brokenTower() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] < 0) {
					map[i][j] = 0;
				}
			}
		}
	}

	// 공격자를 고르는 메소드
	private static Point selectAttacker(int t) {
		// 공격자 : 부서지지 않은 포탑 중 가장 약한 포탑 -> N+M 만큼 공격력 증가
		// 가장 약한 포탑 선정 기준

		// 1-1. 공격력이 가장 낮은 포탑
		List<Point> weaks = new ArrayList<>();
		int min = MAX_POWER;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && min >= map[i][j]) {
					min = map[i][j];
					for (int k = weaks.size() - 1; k >= 0; k--) {
						Point p = weaks.get(k);
						if (map[p.x][p.y] > min)
							weaks.remove(p);
					}
					weaks.add(new Point(i, j));
				}
			}
		}
		// 만약 1개라면 리턴
		if (weaks.size() == 1) {
			Point attacker = weaks.get(0);
			gethandicap(attacker, t);
			return attacker;
		}
		// 1-2. 만약 2개 이상이라면 가장 최근에 공격한 포탑(시점 0에 모든 포탑은 모두 공격한 경험이 있다고 가정)
		List<Point> nextWeaks = new ArrayList<>();
		int max = 0;
		for (Point p : weaks) {
			int attackTime = lastAttack.get(p.getIndex());
			if (max <= attackTime) {
				max = attackTime;
				for (int k = nextWeaks.size() - 1; k >= 0; k--) {
					Point p1 = nextWeaks.get(k);
					if (lastAttack.get(p1.getIndex()) < max) {
						nextWeaks.remove(p1);
					}

				}
				nextWeaks.add(p);
			}
		}
		// 만약 1개라면 리턴
		if (nextWeaks.size() == 1) {
			Point attacker = nextWeaks.get(0);
			gethandicap(attacker, t);
			return attacker;
		}
		// 1-3. 만약 2개 이상이라면 각 포탑 위치의 행과 열의 합이 가장 큰 포탑
		weaks.clear();
		max = 0;
		for (Point p : nextWeaks) {
			if (max <= p.getSum()) {
				max = p.getSum();
				for (int k = weaks.size() - 1; k >= 0; k--) {
					Point p1 = weaks.get(k);
					if (max > p1.getSum())
						weaks.remove(p1);

				}
				weaks.add(p);
			}
		}
		// 만약 1개라면 리턴
		if (weaks.size() == 1) {
			Point attacker = weaks.get(0);
			gethandicap(attacker, t);
			return attacker;
		}
		// 1-4. 만약 2개 이상이라면 각 포탑 위치의 열 값이 가장 큰 포탑
		nextWeaks.clear();
		max = 0;
		for (Point p : weaks) {
			if (max <= p.y) {
				max = p.y;
				for (int k = nextWeaks.size() - 1; k >= 0; k--) {
					Point p1 = nextWeaks.get(k);
					if (max > p1.y)
						nextWeaks.remove(p1);
				}
				nextWeaks.add(p);
			}
		}
		// 공격자 핸디캡 적용
		Point attacker = nextWeaks.get(0);
		gethandicap(attacker, t);
		return attacker;
	}

	private static void gethandicap(Point attacker, int t) {
		int addPower = N + M;
		map[attacker.x][attacker.y] += addPower;
//		System.out.println("attacker add power " + addPower + " total power is " + map[attacker.x][attacker.y]);
		// 공격시간 수정
		lastAttack.put(attacker.getIndex(), t);
		// 공격자 포탑 체크
		isRelated[attacker.x][attacker.y] = true;
	}

	// 가장 강한 포탑을 고르는 메소드
	private static Point selectPowerful(Point weaks) {
		// 1-1. 공격력이 가장 높은 포탑
		List<Point> powers = new ArrayList<>();
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 공격자 제외
				if (i == weaks.x && j == weaks.y)
					continue;
				if (max <= map[i][j]) {
					max = map[i][j];
					for (int k = powers.size() - 1; k >= 0; k--) {
						Point p = powers.get(k);
						if (map[p.x][p.y] < max)
							powers.remove(p);
					}
					powers.add(new Point(i, j));
				}
			}
		}
		// 만약 1개라면 리턴
		if (powers.size() == 1) {
			Point attacked = powers.get(0);
			// 공격당할 포탑 체크
			isRelated[attacked.x][attacked.y] = true;
			return attacked;
		}
		// 1-2. 만약 2개 이상이라면 공격한지 가장 오래된 포탑(시점 0에 모든 포탑은 모두 공격한 경험이 있다고 가정)
		List<Point> nextPowers = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		for (Point p : powers) {
			int attackTime = lastAttack.get(p.getIndex());
			if (min >= attackTime) {
				min = attackTime;
				for (int k = nextPowers.size() - 1; k >= 0; k--) {
					Point p1 = nextPowers.get(k);
					if (lastAttack.get(p1.getIndex()) > min) {
						nextPowers.remove(p1);
					}
				}
				nextPowers.add(p);
			}
		}
		// 만약 1개라면 리턴
		if (nextPowers.size() == 1) {
			Point attacked = nextPowers.get(0);
			// 공격당할 포탑 체크
			isRelated[attacked.x][attacked.y] = true;
			return attacked;
		}
		// 1-3. 만약 2개 이상이라면 각 포탑 위치의 행과 열의 합이 가장 작은 포탑
		powers.clear();
		min = Integer.MAX_VALUE;
		for (Point p : nextPowers) {
			if (min >= p.getSum()) {
				min = p.getSum();
				for (int k = powers.size() - 1; k >= 0; k--) {
					Point p1 = powers.get(k);
					if (min < p1.getSum())
						powers.remove(p1);
				}
				powers.add(p);
			}
		}
		// 만약 1개라면 리턴
		if (powers.size() == 1) {
			Point attacked = powers.get(0);
			// 공격당할 포탑 체크
			isRelated[attacked.x][attacked.y] = true;
			return attacked;
		}
		// 1-4. 만약 2개 이상이라면 각 포탑 위치의 열 값이 가장 작은 포탑
		nextPowers.clear();
		min = M;
		for (Point p : powers) {
			if (min >= p.y) {
				min = p.y;
				for (int k = nextPowers.size() - 1; k >= 0; k--) {
					Point p1 = nextPowers.get(k);
					if (min < p1.y)
						nextPowers.remove(p1);

				}
				nextPowers.add(p);
			}
		}

		Point attacked = nextPowers.get(0);
		// 공격당할 포탑 체크
		isRelated[attacked.x][attacked.y] = true;
		return attacked;
	}

	// 2-1. 레이저 공격 시도
	// 공격자의 위치에서 공격 대상 포탑까지의 최단 경로로 공격
	// 만약 그러한 경로가 존재하지 않는다면 포탑공격 진행
	private static boolean attackLaser(Point attacker, Point attacked) {
		Queue<Route> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Route(attacker));
		visited[attacker.x][attacker.y] = true;

		while (!queue.isEmpty()) {
			Route cur = queue.poll();
			if (cur.p.x == attacked.x && cur.p.y == attacked.y) {
				List<Point> list = cur.routes;
//				System.out.println("attack route is " + list);
				int power = map[attacker.x][attacker.y];
				int halfPower = map[attacker.x][attacker.y] / 2;
				for (Point p : list) {
					if (attacker.x == p.x && attacker.y == p.y)
						continue;
					map[p.x][p.y] -= halfPower;
					// 공격당한 포탑 체크
					isRelated[p.x][p.y] = true;
				}
				map[attacked.x][attacked.y] -= power;
//				System.out.println("attack using laser");
				return true;
			}
			for (int d = 0; d < 4; d++) {
				int nx = cur.p.x + dx[d];
				int ny = cur.p.y + dy[d];

				// 만약 가장자리에서 막힌다면
				if (!inRange(nx, ny)) {
					// 반대편으로 나옴
					Point next = getNext(nx, ny);
					nx = next.x;
					ny = next.y;
				}

				// 부서진 포탑으로는 움직일 수 없음
				if (map[nx][ny] == 0 || visited[nx][ny])
					continue;

				List<Point> list = new ArrayList<>();
				visited[nx][ny] = true;
				for (int i = 0; i < cur.routes.size(); i++) {
					list.add(cur.routes.get(i));
				}
				list.add(cur.p);
				queue.offer(new Route(new Point(nx, ny), list));
			}
		}
		return false;
	}

	static int[] dx8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	// 2-2. 포탄 공격
	private static void attackBomb(Point attacker, Point attacked) {
		int power = map[attacker.x][attacker.y];
		int halfPower = map[attacker.x][attacker.y] / 2;

		map[attacked.x][attacked.y] -= power;
		for (int d = 0; d < 8; d++) {
			int nx = attacked.x + dx8[d];
			int ny = attacked.y + dy8[d];
			if (!inRange(nx, ny)) {
				Point next = getNext(nx, ny);
				nx = next.x;
				ny = next.y;
			}
			if (attacker.x == nx && attacker.y == ny)
				continue;
			if (attacked.x == nx && attacked.y == ny)
				continue;
			map[nx][ny] -= halfPower;
			// 공격당한 포탑 체크
			isRelated[nx][ny] = true;
		}
	}

	private static Point getNext(int nx, int ny) {
		if (nx < 0) {
			nx = N - 1;
		}
		if (nx >= N) {
			nx = 0;
		}
		if (ny < 0) {
			ny = M - 1;
		}
		if (ny >= M) {
			ny = 0;
		}
		return new Point(nx, ny);
	}

	private static boolean inRange(int nx, int ny) {
		return (nx >= 0 && nx < N && ny >= 0 && ny < M);
	}

	static void print(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
