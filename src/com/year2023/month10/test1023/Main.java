package com.year2023.month10.test1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		HashMap<Integer, Integer> hash = new HashMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if (order.equals("add")) {
				int k = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				hash.put(k, v);

			} else if (order.endsWith("remove")) {
				int k = Integer.parseInt(st.nextToken());

				hash.remove(k);

			} else {
				int k = Integer.parseInt(st.nextToken());

				if (hash.containsKey(k)) {
					System.out.println(hash.get(k));
				} else {
					System.out.println("None");
				}
			}
		}
	}

}
