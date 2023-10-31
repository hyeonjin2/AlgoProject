package com.year2023.month10.day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 퍼즐
public class BaekJoon1525 {

	static class Point {
		int x, y;
		int cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}

	static int[][] map;
	static Point start;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[3][3];
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					start = new Point(i, j, 0);
				}
			}
		}
		// 조건
		// 1. 0이 (2,2)로 이동
		// 2. 1,2,3,4,5,6,7,8 순서대로 칸에 존재
		// 3. 최소 이동 횟수 -> 불가능한 경우 -1
		System.out.println(move());
	}

	private static int move() {
		Queue<Point> queue = new ArrayDeque<>();
//		boolean[][][] visited=new boolean[][3][3];
		
		return -1;
	}

	private static boolean isEnd() {
		if (map[2][2] != 0)
			return false;
		int num = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] != num++)
					return false;
			}
		}
		return true;
	}

}
