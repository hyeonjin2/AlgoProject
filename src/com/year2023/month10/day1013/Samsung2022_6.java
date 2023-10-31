package com.year2023.month10.day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

// 2022 하반기 오전 2번
public class Samsung2022_6 {
	public static StringTokenizer st;
	public static int n, m, q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		q = Integer.parseInt(br.readLine());
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			switch (order) {
			// 공장 설립
			case 100:
				buildFactory();
				break;
			// 물건 하차
			case 200:
				dropOff();
				break;
			// 물건 제거
			case 300:
				remove();
				break;
			// 물건 확인
			case 400:
				find();
				break;
			// 벨트 고장
			case 500:
				brokenBelt();
				break;
			default:
				break;
			}
		}
	}

	// 각 id별로 상자 무게 저장
	public static HashMap<Integer, Integer> weight = new HashMap<>();

	// id에 해당하는 상자의 nxt값과 prv값 관리
	public static HashMap<Integer, Integer> prv = new HashMap<>();
	public static HashMap<Integer, Integer> nxt = new HashMap<>();

	// 벨트별로 head, tail 관리
	public static int[] head = new int[10];
	public static int[] tail = new int[10];

	// 벨트가 망가졌는지 여부
	public static boolean[] broken = new boolean[10];

	// 벨트 번호
	public static HashMap<Integer, Integer> beltNum = new HashMap<>();

	private static void buildFactory() {
		// 공장 정보 입력
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int[] ids = new int[n];
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			ids[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		// id마다 무게 hash map에 입력
		for (int i = 0; i < n; i++) {
			weight.put(ids[i], weights[i]);
		}

		// 벨트 별로 상자 넣기
		int size = n / m;
		for (int i = 0; i < m; i++) {
			head[i] = ids[i * size];
			tail[i] = ids[(i + 1) * size - 1];
			for (int j = i * size; j < (i + 1) * size; j++) {
				// 상자마다 벨트번호 저장
				beltNum.put(ids[j], i + 1);

				// nxt, prv 설정
				if (j < (i + 1) * size - 1) {
					nxt.put(ids[j], ids[j + 1]);
					prv.put(ids[j + 1], ids[j]);
				}
			}
		}
	}

	private static void dropOff() {
		int wMax = Integer.parseInt(st.nextToken());
		int wSum = 0;
		// 벨트별로 앞에 상자 보기
		for (int i = 0; i < m; i++) {
			// 망가진 벨트 넘어가기
			if (broken[i])
				continue;

			// 벨트 head 확인
			if (head[i] != 0) {
				int id = head[i];
				int w = weight.get(id);

				// 무게가 wMax 이하라면 없애고 무게 더하기
				if (w <= wMax) {
					wSum += w;
					removeId(id, true);
				}
				// 그렇지 않다면
				// 상자 맨 뒤로 올리기
				else if (nxt.get(id) != 0) {
					removeId(id, false);
					pushId(tail[i], id);
				}
			}
		}
		System.out.println(wSum);
	}

	// targetId 뒤에 id 상자 올리기
	private static void pushId(int targetId, int id) {
		nxt.put(targetId, id);
		prv.put(id, targetId);

		// 만약 targetId가 tail이었다면
		// tail 변경
		int bNum = beltNum.get(targetId) - 1;
		if (tail[bNum] == targetId)
			tail[bNum] = id;
	}

	private static void removeId(int id, boolean removeBelt) {
		int bNum = beltNum.get(id) - 1;
		// 벨드에서 id 제거
		if (removeBelt)
			beltNum.put(id, 0);

		// 하나 남은 원소라면
		// head, tail이 사라지면 삭제
		if (head[bNum] == tail[bNum])
			head[bNum] = tail[bNum] = 0;

		// head가 삭제되면 -> head 변경
		else if (id == head[bNum]) {
			int nid = nxt.get(id);
			head[bNum] = nid;
			prv.put(nid, 0);
		}
		// tail이 삭제되면 -> tail 변경
		else if (id == tail[bNum]) {
			int pid = prv.get(id);
			tail[bNum] = pid;
			nxt.put(pid, 0);
		}
		// 중간에 있는 상자가 삭제되면
		else {
			int pid = prv.get(id), nid = nxt.get(id);
			nxt.put(pid, nid);
			prv.put(nid, pid);
		}

		// nxt, prv 값 지워주기
		nxt.put(id, 0);
		prv.put(id, 0);

	}

	private static void remove() {
		int rId = Integer.parseInt(st.nextToken());

		// 이미 삭제된 상자라면 -1 출력
		if (beltNum.getOrDefault(rId, 0) == 0) {
			System.out.println(-1);
			return;
		}

		// 해당 상자 제거
		removeId(rId, true);
		System.out.println(rId);
	}

	private static void find() {
		int fId = Integer.parseInt(st.nextToken());

		// 없는 상자라면 -1 출력
		if (beltNum.getOrDefault(fId, 0) == 0) {
			System.out.println(-1);
			return;
		}

		// 상자를 찾아 맨 앞으로 가져오기
		int bNum = beltNum.get(fId) - 1;
		if (head[bNum] != fId) {
			int origTail = tail[bNum];
			int origHead = head[bNum];

			int nowTail = prv.get(fId);
			tail[bNum] = nowTail;
			nxt.put(nowTail, 0);

			nxt.put(origTail, origHead);
			prv.put(origHead, origTail);

			head[bNum] = fId;
		}
		System.out.println(bNum + 1);
	}

	private static void brokenBelt() {
		int bNum = Integer.parseInt(st.nextToken()) - 1;

		if (broken[bNum]) {
			System.out.println(-1);
			return;
		}

		broken[bNum] = true;

		// 만약 빈 벨트라면 끝
		if (head[bNum] == 0) {
			System.out.println(bNum + 1);
			return;
		}

		// 아직 망가지지 않은 벨트 위로 상자 옮겨주기
		int nxtNum = bNum;
		while (true) {
			nxtNum = (nxtNum + 1) % m;

			// 최초로 망가지지 않은 벨트를 발견하면 상자 옮기기
			if (!broken[nxtNum]) {
				// 만약 벨트가 비어있다면
				// 그대로 옮기기
				if (tail[nxtNum] == 0) {
					head[nxtNum] = head[bNum];
					tail[nxtNum] = tail[bNum];
				} else {
					// 해당 위치로 상자 전부 옮기기
					// head만 tail뒤에 붙이고, tail을 바꾸면 됨
					pushId(tail[nxtNum], head[bNum]);
					tail[nxtNum] = tail[bNum];
				}
				// beltNum 변경하기
				int id = head[bNum];
				while (id != 0) {
					beltNum.put(id, nxtNum + 1);
					id = nxt.getOrDefault(id, 0);
				}

				head[bNum] = tail[bNum] = 0;
				break;
			}
		}
		System.out.println(bNum + 1);
	}

}
