package com.year2023.month10.day1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Point;

// 2022 하반기 오전 1번
public class Samsung2022_5 {

	static int N, M, K;
	static ArrayList<Integer>[][] gun;
	static Player[] players;

	// 0, 1, 2, 3
	// 상, 우, 하, 좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Player {
		int x, y, d;
		int s, g, p;

		public Player(int x, int y, int d, int s, int g, int p) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.s = s;
			this.g = g;
			this.p = p;
		}

		public void getPoint(int p) {
			this.p += p;
		}

		public void loseGun() {
			if (this.g != 0) {
				gun[x][y].add(this.g);
//				System.out.println("in " + x + " " + y + " put down a gun " + this.g);
			}
			this.g = 0;
		}

		public void getGun() {
			int max = 0;
			int index = 0;
//			System.out.print(" in " + x + " " + y + " # p of gun is " + gun[x][y].size());
			// 공격력이 가장 높은 총 찾기
			for (int i = 0; i < gun[x][y].size(); i++) {
				if (max < gun[x][y].get(i)) {
					max = gun[x][y].get(i);
					index = i;
				}
			}
			if (this.g < max) {
				gun[x][y].remove(index);
				if (this.g != 0)
					gun[x][y].add(this.g);
				this.g = max;
//				System.out.println(" get a gun power " + this.g);
			} else {
//				System.out.println(" fail to get a gun");
			}
		}

		public void goNext(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Player [x=" + x + ", y=" + y + ", d=" + d + ", s=" + s + ", g=" + g + ", p=" + p + "]\n";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // matrix size = N*N
		M = Integer.parseInt(st.nextToken()); // # of player
		K = Integer.parseInt(st.nextToken()); // # of round

		gun = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				gun[i][j] = new ArrayList<Integer>();
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				gun[i][j].add(Integer.parseInt(st.nextToken()));
			}
		}

		players = new Player[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			players[i] = new Player(x, y, d, s, 0, 0);
		}

		int[] result = new int[M];
		for (int i = 1; i <= K; i++) {
			result = play();
//			System.out.println("=============== " + i + "번째 round 결과 ===============");
//			System.out.println(Arrays.toString(players));
		}

		for (int i = 0; i < M; i++) {
			System.out.print(result[i] + " ");
		}
	}

	private static int[] play() {
		int[] result = new int[M];
		// 1번부터 차례대로 이동
		for (int i = 1; i <= M; i++) {
			move(i);
//			System.out.println("player " + i + " move");
//			System.out.println(Arrays.toString(players));
		}
		// 포인트 취합
		for (int i = 1; i <= M; i++) {
			result[i - 1] = players[i].p;
		}
		return result;
	}

	static void move(int i) {
		int x = players[i].x;
		int y = players[i].y;
		int d = players[i].d;
		int s = players[i].s;
		int g = players[i].g;
		int p = players[i].p;

		// player가 다음으로 이동
		Point next = moveNext(i);

		// 이동하려는 칸에 다른 플레이어가 존재한다면
		int j = isExist(next.x, next.y, i);
		if (j > 0) {
			// 싸우기
//			System.out.println("go fight");
			fight(i, j);
		}
		// 총 줍기
//		System.out.print(i + " player");
		players[i].getGun();

	}

	static void fight(int num1, int num2) {
//		System.out.println("fight " + num1 + " vs " + num2);
		// 전체 공격력 = 플레이어의 초기 공격력 + 총 공격력
		int power1 = players[num1].s + players[num1].g;
		int power2 = players[num2].s + players[num2].g;

		// 포인트 = 절댓값(공격력1-공격력2)
		int p = Math.abs(power1 - power2);

		// player2가 더 약한 경우
		if (power1 > power2) {
			// player1 포인트 획득
//			System.out.println("player " + num1 + " get " + p + " points");
			players[num1].getPoint(p);
			// player2 총 내려놓기
			players[num2].loseGun();
			// player1 총 줍기
			players[num1].getGun();
			// player2 이동
			fightMove(num2);
		}
		// player1이 더 약한 경우 -> player2 포인트 획득, player1 이동
		else if (power1 < power2) {
//			System.out.println("player " + num2 + " get " + p + " points");
			players[num2].getPoint(p);
			players[num1].loseGun();
			players[num2].getGun();
			fightMove(num1);
		}
		// 둘의 전체 공격력이 같은 경우 -> 플레이어 초기 공격력 비교
		else {
			if (players[num1].s > players[num2].s) {
				players[num2].loseGun();
				players[num1].getGun();
				fightMove(num2);
			} else {
				players[num1].loseGun();
				players[num2].getGun();
				fightMove(num1);
			}
		}

	}

	// 플레이어가 다음으로 이동할 좌표를 구하는 메소드
	static Point moveNext(int i) {
		int x = players[i].x;
		int y = players[i].y;
		int d = players[i].d;
		int s = players[i].s;
		int g = players[i].g;
		int p = players[i].p;

		int nx = x + dx[d];
		int ny = y + dy[d];

		// 배열의 범위를 벗어나면 정반대 이동
		while (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			/// d가 0,1,2,3 순으로 바뀜
			d = (d + 2) % 4;
			nx = x + dx[d];
			ny = y + dy[d];
		}

		x = nx;
		y = ny;

		players[i].goNext(x, y, d);

		return new Point(x, y);
	}

	static void fightMove(int i) {
		int x = players[i].x;
		int y = players[i].y;
		int d = players[i].d;
		int s = players[i].s;
		int g = players[i].g;
		int p = players[i].p;

		int nx = x + dx[d];
		int ny = y + dy[d];

		// 배열의 범위를 벗어나면 오른쪽으로 90도씩 회전
		while (nx < 0 || nx >= N || ny < 0 || ny >= N || isExist(nx, ny, i) > 0) {
			d = (++d) % 4;
			nx = x + dx[d];
			ny = y + dy[d];
		}
		x = nx;
		y = ny;
		players[i].goNext(x, y, d);
		players[i].getGun();

	}

	static int isExist(int x, int y, int num) {
		for (int i = 1; i <= M; i++) {
			if (i == num)
				continue;
			if (players[i].x == x && players[i].y == y) {
				return i;
			}
		}
		return -1;
	}

	static void print(int[][] arr) {
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
