package com.year2023.month07.day0705;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 서강그라운드
public class BaekJoon14938 {

	static class Node {
		int vertex, weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

	}

	static class Vertex implements Comparable<Vertex> {
		int no, weigth;

		public Vertex(int no, int weigth) {
			this.no = no;
			this.weigth = weigth;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weigth - o.weigth;
		}

	}

	static int N, M, R;
	static int[] item; // 아이템 수
	static Node[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		item = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		// 인접리스트 초기화
		adjList = new Node[N + 1];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, dijkstra(i));
		}

		System.out.println(ans);

	}

	private static int dijkstra(int start) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(start, 0));

		boolean[] visited = new boolean[N + 1]; // 방문체크 배열
		int[] D = new int[N + 1]; // 최단거리를 저장할 배열

		Arrays.fill(D, Integer.MAX_VALUE);

		// 출발 정점 처리
		D[start] = 0;

		while (!pq.isEmpty()) {
			Vertex minVertex = pq.poll();
			if (visited[minVertex.no])
				continue;

			visited[minVertex.no] = true;
			for (Node temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && D[temp.vertex] > D[minVertex.no] + temp.weight) {
					D[temp.vertex] = D[minVertex.no] + temp.weight;
					pq.offer(new Vertex(temp.vertex, D[temp.vertex]));
				}
			}
		}

		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (D[i] <= M) {
				result += item[i];
			}
		}

		return result;
	}

}
