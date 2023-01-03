package com.day0103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 게임 개발
public class BaekJoon1516 {
    static class Node {
        int to, weigth;
        Node from;

        public Node(int to, int weigth, Node from) {
            this.to = to;
            this.weigth = weigth;
            this.from = from;
        }
    }

    static int N, ans;
    static int[] order;
    static Node[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        order = new int[N];
        adjList = new Node[N];

        StringTokenizer st;
        for (int from = 0; from < N; from++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, adjList[from]);
            order[from]++;
        }
        ans = 0;
        TopologySort();

        System.out.println(ans);
    }

    private static void TopologySort() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (order[i] == 0) {
                ans += adjList[i].weigth;
            }
        }
    }
}
