package com.year2023.month10.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 문자열 교집합
public class SWEA2948 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 첫번째 문자열 입력받기
			HashSet<String> hash1 = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				hash1.add(st.nextToken());
			}

			// 두번째 문자열 입력받기
			int result = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				if (hash1.contains(st.nextToken()))
					result++;
			}

			System.out.println("#" + tc + " " + result);
		}
	}

}
