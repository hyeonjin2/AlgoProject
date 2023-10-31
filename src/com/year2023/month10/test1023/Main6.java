package com.year2023.month10.test1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main6 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);
		int answer = 0;
		// 4 6 8 8 9
		for (int i = 0; i < N; i++) {
			int left = i;
			int right = N - 1;
			while (left < right) {
				int temp = nums[left] * nums[right];
				if (temp == K) {
					answer++;
					if (left < N && nums[left] == nums[left + 1]) {
						left++;
					} else if (right > 0 && nums[right] == nums[right - 1]) {
						right--;
					} else {
						left++;
					}
				} else if (temp < K) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.println(answer);
	}

}
