package com.year2023.month01.day0103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 게임 개발
public class BaekJoon1516 {
    static class Node {
        int vertex;
        Node to;

        public Node(int from, Node to) {
            this.vertex = from;
            this.to = to;
        }
    }

    static int N;
    static int[] inDegree, build, times;
    static Node[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        inDegree = new int[N + 1];
        times = new int[N + 1];
        build = new int[N + 1];
        adjList = new Node[N + 1];

        StringTokenizer st;
        for (int from = 1; from <= N; from++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            times[from] = time;
            while (to != -1) {
                adjList[to] = new Node(from, adjList[to]);
                inDegree[from]++;
                to = Integer.parseInt(st.nextToken());
            }
        }
        TopologySort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(build[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void TopologySort() {
        PriorityQueue<Integer> queue = new PriorityQueue<>((e1, e2) -> {
            return times[e1] - times[e2];
        });
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                build[i] = times[i];
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node temp = adjList[cur]; temp != null; temp = temp.to) {
                build[temp.vertex] = Math.max(build[temp.vertex], build[cur] + times[temp.vertex]);
                if (--inDegree[temp.vertex] == 0) {
                    queue.offer(temp.vertex);
                }
            }
        }
    }
}
