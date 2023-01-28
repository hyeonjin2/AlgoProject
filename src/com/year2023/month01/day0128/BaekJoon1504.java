package com.year2023.month01.day0128;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 특정한 최단 경로
public class BaekJoon1504 {

  static class Node {
    int vertex, weight;
    Node next;

    public Node(int vertex, int weight, Node next) {
      this.vertex = vertex;
      this.weight = weight;
      this.next = next;
    }
  }

  static int N, E;
  static final int INF = 200_000_000;
  static Node[] adjList;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    adjList = new Node[N + 1];

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      adjList[from] = new Node(to, weight, adjList[from]);
      adjList[to] = new Node(from, weight, adjList[to]);
    }
    st = new StringTokenizer(br.readLine());
    int u = Integer.parseInt(st.nextToken());
    int v = Integer.parseInt(st.nextToken());


    // result1 : 1->u->v->N
    int result1 = 0;
    // 1 -> u 최단 경로
    result1 += dijkstra(1, u);
    // v -> N 최단 경로
    result1 += dijkstra(v, N);
    // u -> v 최단 경로
    result1 += dijkstra(u, v);

    // result2 : 1->v->u->N
    int result2 = 0;
    // 1 -> v 최단 경로
    result2 += dijkstra(1, v);
    // u -> N 최단 경로
    result2 += dijkstra(u, N);
    // v -> u 최단 경로
    result2 += dijkstra(v, u);

    int ans;
    if (result1 >= INF && result2 >= INF) {
      ans = -1;
    } else {
      ans = Math.min(result1, result2);
    }
    System.out.println(ans);
  }

  // start 정점에서 end 정점으로 가는 경로의 최단 경로
  private static int dijkstra(int start, int end) {
    int[] dist = new int[N + 1];
    boolean[] visited = new boolean[N + 1];

    Arrays.fill(dist, INF);
    Arrays.fill(visited, false);

    dist[start] = 0;
    for (int i = 1; i <= N; i++) {
      long min = Long.MAX_VALUE;
      int minVertex = 0;

      for (int j = 1; j <= N; j++) {
        if (!visited[j] && min > dist[j]) {
          min = dist[j];
          minVertex = j;
        }
      }

      visited[minVertex] = true;
      if (minVertex == end) {
        break;
      }
      for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
        if (!visited[temp.vertex] && dist[temp.vertex] > temp.weight + dist[minVertex]) {
          dist[temp.vertex] = temp.weight + dist[minVertex];
        }
      }
    }
    return dist[end];
  }
}
