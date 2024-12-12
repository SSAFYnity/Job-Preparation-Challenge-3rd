import java.util.*;
class Solution {
    Deque<Integer> leftDeque = new ArrayDeque<>();
    Deque<Integer> rightDeque = new ArrayDeque<>();
    Deque<Deque<Integer>> centerDeque = new ArrayDeque<>();
    public int[][] solution(int[][] rc, String[] operations) {
        setDeque(rc);
        for (String command : operations) {
            if (command.equals("Rotate")) {
                rotate();
            } else {
                shiftRow();
            }
        }
        return getAnswer(rc.length, rc[0].length);
    }
    private int[][] getAnswer(int rowSize, int columnSize) {
        int[][] answer = new int[rowSize][columnSize];
        for (int row = 0; row < rowSize; row++) {
            Deque<Integer> centerRow = centerDeque.pollFirst();
            for (int column = 0; column < columnSize; column++) {
                if (column == 0) {
                    answer[row][column] = leftDeque.pollFirst();
                } else if (column == columnSize - 1) {
                    answer[row][column] = rightDeque.pollFirst();
                } else {
                    answer[row][column] = centerRow.pollFirst();
                }
            }
        }
        return answer;


    }
    private void rotate() {
        Deque<Integer> centerFirstRow = centerDeque.peekFirst();
        // left To Top
        int left = leftDeque.pollFirst();
        centerFirstRow.addFirst(left);
        // Top To right
        int top = centerFirstRow.pollLast();
        rightDeque.addFirst(top);
        // Right To Bottom
        Deque<Integer> centerLastRow = centerDeque.peekLast();
        int right = rightDeque.pollLast();
        centerLastRow.addLast(right);
        // Bottom To Left
        int bottom = centerLastRow.pollFirst();
        leftDeque.addLast(bottom);
    }
    private void shiftRow() {
        int left = leftDeque.pollLast();
        leftDeque.addFirst(left);
        int right = rightDeque.pollLast();
        rightDeque.addFirst(right);
        Deque<Integer> centerLastRow = centerDeque.pollLast();
        centerDeque.addFirst(centerLastRow);
    }

    private void setDeque(int[][] rc) {
        int rowSize = rc.length;
        int columnSize = rc[0].length;
        for (int row = 0; row < rowSize; row++) {
            Deque<Integer> centerRow = new ArrayDeque<>();
            for (int column = 0; column < columnSize; column++) {
                if (column == 0) {
                    leftDeque.addLast(rc[row][column]);
                } else if (column == columnSize-1) {
                    rightDeque.addLast(rc[row][column]);
                } else {
                    centerRow.addLast(rc[row][column]);
                }
            }
            centerDeque.addLast(centerRow);
        }
    }
}