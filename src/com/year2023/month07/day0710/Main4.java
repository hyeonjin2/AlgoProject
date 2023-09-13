package com.year2023.month07.day0710;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4 {

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
		int r = 0;
		int c = N - 1;
		int max = 0;

		while (!(r == N - 1 && c == 0)) {
			int max1 = 0;
			int max2 = 0;
			int max3 = 0;
			if (r - 1 >= 0) {
				max1 = map[r - 1][c];
			}
			if (r - 1 >= 0 && c + 1 < N) {
				max2 = map[r - 1][c + 1];
			}
			if (c + 1 < N) {
				max3 = map[r][c + 1];
			}
			int temp = Math.max(max1, Math.max(max2, max3));
			if (temp == max1) {
				r++;
			} else if (temp == max2) {
				r++;
				c--;
			} else {
				c--;
			}
			max += temp;
			System.out.println(max);
		}
		System.out.println(max);
	}

}
