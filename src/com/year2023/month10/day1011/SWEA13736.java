package com.year2023.month10.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사탕 분배
public class SWEA13736 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			long result = solution(A, B, K);

			System.out.printf("#%d %d\n", tc, result);
		}
	}

	static long solution(int num1, int num2, int cnt) {
		int sum = num1 + num2;
		int max = sum / 2;
		long result = ((long) get2Power(cnt, sum) * (long) num1) % sum;
		return result > max ? sum - result : result;
	}

	// (2^cnt) % mod
	static int get2Power(int cnt, int mod) {
		long result = 1;
		long num = 2;
		// 비재귀 형식으로 거듭제곱 구하기
		while (cnt > 0) {
			if (cnt % 2 == 1) {
				result = (result * num) % mod;
			}
			num = (num * num) % mod;
			cnt /= 2;
		}
		return (int) result;
	}

}
