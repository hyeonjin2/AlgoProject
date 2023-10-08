package com.year2023.month10.day1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 공통조상
public class SWEA1248 {

	static class Node {
		int parents;
		List<Integer> children;

		public Node() {
			this.parents = 0;
			this.children = new ArrayList<>();
		}

	}

	static int V, E;
	static Node[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			tree = new Node[V + 1];

			for (int i = 0; i < V + 1; i++) {
				tree[i] = new Node();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());

				tree[parent].children.add(child);
				tree[child].parents = parent;
			}

			StringBuilder answer = new StringBuilder();
			int ancestor = findAncestor(a, b);
			int count = numSubtree(ancestor);

			answer.append(ancestor).append(" ").append(count);

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	// 공통 조상 찾기
	private static int findAncestor(int a, int b) {
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();
		findAncestors(a, listA);
		findAncestors(b, listB);

		int ancestor = 1;
		for (int i = 0; i < V; i++) {
			if (!listA.get(i).equals(listB.get(i)))
				break;
			ancestor = listA.get(i);
		}
		return ancestor;
	}

	// 조상 찾기
	private static void findAncestors(int info, List<Integer> ancestor) {
		int parent = tree[info].parents;
		if (parent != 0) {
			findAncestors(parent, ancestor);
		}

		ancestor.add(info);

	}

	// 서브트리 개수 찾기
	private static int numSubtree(int node) {
		if (tree[node].children.size() == 0)
			return 1;

		int count = 1;
		for (int child : tree[node].children) {
			count += numSubtree(child);
		}
		return count;
	}
}
