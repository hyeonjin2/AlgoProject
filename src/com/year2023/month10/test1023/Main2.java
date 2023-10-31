package com.year2023.month10.test1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws IOException {
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
		int r1 = Integer.parseInt(st.nextToken()) - 1;
		int c1 = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		int r2 = Integer.parseInt(st.nextToken()) - 1;
		int c2 = Integer.parseInt(st.nextToken()) - 1;

		int max = 0;
		for (int i = r1; i <= r2; i++) {
			for (int j = c1; j <= c2; j++) {
				if (map[i][j] > max)
					max = map[i][j];
			}
		}
		System.out.println(max);
	}

}
