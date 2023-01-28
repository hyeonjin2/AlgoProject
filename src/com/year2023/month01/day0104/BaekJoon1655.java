package com.year2023.month01.day0104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 가운데를 말해요
public class BaekJoon1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        // PQ 두개 생성
        // minHeap : 중간값보다 큰 값들이 들어가는 큐
        // maxHeap : 중간값보다 작거나 같은 값들이 들어가는 큐
        // -> 중간값이 바뀌게 될 경우 큐에 있는 값 중에서 큰 값이 나와야 하므로 maxHeap 사용
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((e1, e2) -> e2 - e1);

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            // 수의 개수가 홀수이면 가운데 값이 중간값
            if (minHeap.size() == maxHeap.size()) {
                maxHeap.offer(num);
            }
            // 수의 개수가 짝수이면 가운데 수 중 작은 값이 중간값
            else {
                minHeap.offer(num);
            }
            // 중간값을 기준으로 maxHeap, minHeap이 정한 규칙을 따르도록 정렬
            if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                if (minHeap.peek() < maxHeap.peek()) {
                    int temp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(temp);
                }
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
