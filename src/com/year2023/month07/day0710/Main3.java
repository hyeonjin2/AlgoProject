package com.year2023.month07.day0710;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i == r - 1 || j == c - 1) && map[i][j] < map[r - 1][c - 1])
					map[i][j] = 0;
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

}
