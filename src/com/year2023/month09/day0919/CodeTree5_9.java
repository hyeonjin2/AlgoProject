package com.year2023.month09.day0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CodeTree5_9 {

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

		// d[i][j]: i,j 칸에 도달할 때까지 경로에 있던 숫자 중의 최솟값
		int[][] d = new int[N][N];

		// 초기화
		d[0][0] = map[0][0];

		// i=0일 때 오른쪽으로만 이동 가능
		for (int j = 1; j < N; j++) {
			d[0][j] = Math.min(d[0][j - 1], map[0][j]);
		}

		// j=0일 때 아래쪽으로만 이동 가능
		for (int i = 1; i < N; i++) {
			d[i][0] = Math.min(d[i - 1][0], map[i][0]);
		}

		// 탐색하는 위치의 위에 값과 좌측 값 중에 큰 값과
        // 해당 위치의 숫자 중에 최솟값을 구해줍니다.
		// d[i][j] = min(max(d[i-1][j],d[i][j-1]), map[i][j])
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				d[i][j] = Math.min(Math.max(d[i - 1][j], d[i][j - 1]), map[i][j]);
			}
		}
		System.out.println(d[N - 1][N - 1]);
	}

}
