package com.year2023.month09.day0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CodeTree5_8 {

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

		// d[i][j]: i,j에서의 숫자의 최소합
		int[][] d = new int[N][N];

		// d배열 초기화
		// i=0, j=N-1 일 때
		d[0][N - 1] = map[0][N - 1];
		// i=0일 때 -> 왼쪽으로만 이동 가능
		for (int j = N - 2; j >= 0; j--) {
			d[0][j] = d[0][j + 1] + map[0][j];
		}
		// j=N-1일 때 -> 아래쪽으로만 이동 가능
		for (int i = 1; i < N; i++) {
			d[i][N - 1] = d[i - 1][N - 1] + map[i][N - 1];
		}

		// 점화식 이용
		for (int i = 1; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				d[i][j] = Math.min(d[i - 1][j], d[i][j + 1]) + map[i][j];
			}
		}
		System.out.println(d[N - 1][0]);
	}

}
