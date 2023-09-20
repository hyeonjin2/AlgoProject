package com.year2023.month09.day0920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test5_DP2 {

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

		// d[i][j]: i,j칸까지 이동했을 때 경로에 있는 숫자들 중 최댓값
		int[][] d = new int[N][N];
		// initialize
		d[0][0] = map[0][0];
		// i=0 오른쪽으로만 이동 가능
		for (int j = 1; j < N; j++) {
			d[0][j] = Math.max(d[0][j - 1], map[0][j]);
		}
		// j=0 아래쪽으로만 이동 가능
		for (int i = 1; i < N; i++) {
			d[i][0] = Math.max(d[i - 1][0], map[i][0]);
		}
		// 점화식 d[i][j] = max(min(d[i-1][j],d[i][j-1]),map[i][j])
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				d[i][j] = Math.max(Math.min(d[i - 1][j], d[i][j - 1]), map[i][j]);
			}
		}

		System.out.println(d[N - 1][N - 1]);

	}

}
