package com.year2023.month10.day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 힙
public class SWEA2930_Heap {

	static class Heap {
		int[] arr;
		int size = 0;

		Heap() {
			arr = new int[100001];
		}

		void add(int x) {
			// 데이터 마지막 인덱스에 삽입
			arr[++size] = x;

			// 위로 올라가면서 자리 찾기
			int cur = size;
			while (cur > 1) {
				int parent = getParent(cur);
				// 만약 부모 노드보다 현재 노드가 더 크면 -> swap(위로 올라가기)
				if (arr[cur] > arr[parent]) {
					int temp = arr[parent];
					arr[parent] = arr[cur];
					arr[cur] = temp;

					// 바뀐 parent부터 다시 자리 찾기
					cur = parent;
				} else {
					break;
				}
			}
		}

		int poll() {
			int max = arr[1];
			// root값을 가장 마지막 값으로 바꾼다.
			arr[1] = arr[size];
			// 가장 마지막 값을 지운다 -> 0으로 바꾼다.
			arr[size] = 0;
			size--;
			// 바뀐 root값에 따라 heapify를 통해 heap을 정렬한다.
			heapify();

			return max;
		}

		// heapify : 모든 non-leaf node인 p에 대해 level order의 역순으로 node p와 p의 child 중
		// 작은 값(min-heap)을 가진 node가 heap property를 만족할 때 까지 계속해서 swap한다
		void heapify() {
			// root에서부터 비교
			int cur = 1;
			while (getRight(cur) <= size) {
				int larger = cur;
				int left = getLeft(cur);
				int right = getRight(cur);
				// 왼쪽 노드와 비교
				if (arr[left] > arr[larger]) {
					larger = left;
				}
				// 오른쪽 노드와 비교
				if (arr[right] > arr[larger]) {
					larger = right;
				}
				// 만약 자식 노드가 현재 노드보다 클 경우 -> swap(아래로 내려가기)
				if (larger != cur) {
					int temp = arr[cur];
					arr[cur] = arr[larger];
					arr[larger] = temp;
					// larger부터 다시 heapify 진행
					cur = larger;
				} else {
					break;
				}
			}
		}

		boolean isEmpty() {
			return size == 0;
		}

		int getParent(int child) {
			return child >> 1;
		}

		int getLeft(int parent) {
			return parent << 1;
		}

		int getRight(int parent) {
			return parent << 1 | 1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 연산 횟수
			Heap heap = new Heap();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int operator = Integer.parseInt(st.nextToken());
				switch (operator) {
				// 삽입
				case 1:
					int num = Integer.parseInt(st.nextToken());
					heap.add(num);
					break;
				// 삭제
				case 2:
					if (heap.isEmpty())
						sb.append("-1").append(" ");
					else
						sb.append(heap.poll()).append(" ");
					break;
				default:
					break;
				}
			}
			System.out.println("#" + tc + " " + sb);
		}

	}

}
