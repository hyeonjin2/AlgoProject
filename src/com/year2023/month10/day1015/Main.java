package com.year2023.month10.day1015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 기울어진 직사각형
public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dx = { -1, -1, 1, 1 };
		int[] dy = { 1, -1, -1, 1 };

		List<Integer> list = new ArrayList<>();
		for (int x = 3; x < N; x++) {
			for (int y = 1; y < N; y++) {
				int dir = 0;
				int sum = map[x][y];
				boolean finish = false;
				while (!finish) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					while (inRange(nx, ny) && finish) {
						finish = (++dir >= 4);
						nx = x + dx[dir];
						ny = y + dy[dir];
					}
					sum += map[nx][ny];
					if (finish) {
						list.add(sum);
						break;
					}
				}
			}
		}

		int max = 0;
		for (Integer sum : list) {
			if (max < sum) {
				max = sum;
			}
		}
		System.out.println(max);

	}

	private static boolean inRange(int nx, int ny) {
		return (nx >= 0 && nx < N && ny >= 0 && ny < N);
	}

}
