package com.year2023.month09.day0918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CodeTree5_7 {

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

		int[][] d = new int[N][N]; // d[i][j]: map[i][j]에서 가질 수 있는 최댓값
		// 배열 초기화
		d[0][0] = map[0][0];
		// i=0일 때는 오른쪽으로 밖에 이동하지 못한다.
		for (int j = 1; j < N; j++) {
			d[0][j] = d[0][j - 1] + map[0][j];
		}
		// j=0일 때는 아래방향으로 밖에 이동하지 못한다.
		for (int i = 1; i < N; i++) {
			d[i][0] = d[i - 1][0] + map[i][0];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				// 왼쪽 or 위 에서 오는 경우 => [i-1][j] or [i][j-1]
				d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]) + map[i][j];
			}
		}
		System.out.println(d[N - 1][N - 1]);
	}

}
