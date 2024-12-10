import java.util.*;
class Solution {
    private Queue<Integer> pq;
    boolean[] visited;
    public int solution(int[] cards) {
        int size = cards.length;
        pq = new PriorityQueue<>(Comparator.reverseOrder());
        visited = new boolean[size];
        for (int index = 0; index < size; index++) {
            find(cards, index, 0);
        }
        if (pq.size() < 2) {
            return 0;
        }
        return pq.poll()*pq.poll();

    }
    private void find(int[] cards, int index, int count) {
        if (visited[index]) {
            if (count != 0) {
                pq.add(count);
            }
            return;
        }
        visited[index] = true;
        find(cards, cards[index]-1, count+1);
    }
}