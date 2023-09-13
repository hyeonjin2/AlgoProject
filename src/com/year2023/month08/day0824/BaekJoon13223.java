package com.year2023.month08.day0824;

import java.util.Scanner;

// 소금 폭탄
public class BaekJoon13223 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String current = sc.next();
		String target = sc.next();

		String[] curTime = current.split(":");
		String[] tarTime = target.split(":");
		String[] result = new String[3];

		if (current.equals(target)) {
			System.out.println("24:00:00");
		} else {

			for (int i = 2; i >= 0; i--) {
				int cur = Integer.parseInt(curTime[i]);
				int tar = Integer.parseInt(tarTime[i]);

				int calc;

				if (tar < cur && i > 0) {
					// 앞자리 1빼기
					int temp = Integer.parseInt(tarTime[i - 1]) - 1;
					tarTime[i - 1] = String.valueOf(temp);

					// 60더하기
					calc = tar + 60 - cur;
				} else if (tar < cur && i == 0) {

					calc = tar + 24 - cur;
				} else {

					calc = tar - cur;
				}
				String str = String.valueOf(calc);
				String res = "";
				if (str.length() == 1) {
					res = "0" + str;
				} else {
					res = str;
				}
				result[i] = res;
			}

			StringBuilder sb = new StringBuilder();
			sb.append(result[0]).append(":").append(result[1]).append(":").append(result[2]);

			System.out.println(sb);
		}
	}

}
