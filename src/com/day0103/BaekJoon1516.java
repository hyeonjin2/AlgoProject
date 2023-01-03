package com.day0103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

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
//        System.out.println(Arrays.toString(times));
//        System.out.println(Arrays.toString(inDegree));
        TopologySort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(build[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void TopologySort() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            build[cur] += times[cur];

            for (Node temp = adjList[cur]; temp != null; temp = temp.to) {
                if (--inDegree[temp.vertex] == 0) {
                    build[temp.vertex] += build[cur];
                    queue.offer(temp.vertex);
                }
            }
        }
    }
}
/*
4
20 -1
10 -1
1 1 2 -1
1000 1 2 3 -1
 */
