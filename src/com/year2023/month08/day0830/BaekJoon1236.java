package com.year2023.month08.day0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 성 지키기
public class BaekJoon1236 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];
		int[] row = new int[N];
		int[] col = new int[M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'X') {
					row[i]++;
					col[j]++;
				}
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (row[i] == 0 && col[j] == 0) {
					result++;
					row[i]++;
					col[j]++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (row[i] == 0) {
					result++;
					row[i]++;
				} else if (col[j] == 0) {
					result++;
					col[j]++;
				}
			}
		}

		System.out.println(result);

	}
}
