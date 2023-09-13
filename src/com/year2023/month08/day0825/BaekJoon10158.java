package com.year2023.month08.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 개미
public class BaekJoon10158 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		int nx = p;
		int ny = q;

		int dx = 1;
		int dy = 1;

		int modX = t % (N * 2);
		int modY = t % (M * 2);

		while (modX-- > 0) {
			if (nx == N) {
				dx = -1;
			} else if (nx == 0) {
				dx = 1;
			}
			nx += dx;
		}
		while (modY-- > 0) {
			if (ny == M) {
				dy = -1;
			} else if (ny == 0) {
				dy = 1;
			}
			ny += dy;
		}

		System.out.println(nx + " " + ny);
	}

}
