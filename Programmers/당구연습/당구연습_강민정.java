class Solution {
    public int getDistance(int startX, int startY, int endX, int endY) {
        return (int) (Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
    }

    /*
        m: 가로 길이
        n: 세로 길이
        startX, startY: 쳐야 하는 공이 놓인 위치 좌표
        balls: 매 회마다 목표로 해야하는 공들의 위치 좌표를 나타내는 정수 쌍들이 들어있는 2차원 정수배열
    */
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int cnt = balls.length;
        int[] answer = new int[cnt];

        for(int i = 0; i < cnt; i++) {
            int endX = balls[i][0];
            int endY = balls[i][1];
            int distance = Integer.MAX_VALUE;

            // 좌: 반사된 목표를 왼쪽 벽에 대해 대칭
            if (!(startY == endY && startX >= endX)) {
                distance = Math.min(distance, getDistance(startX, startY, endX * (-1), endY));
            }

            // 우: 반사된 목표를 오른쪽 벽에 대해 대칭
            if (!(startY == endY && startX <= endX)) {
                distance = Math.min(distance, getDistance(startX, startY, m + (m - endX), endY));
            }

            // 상: 반사된 목표를 상단 벽에 대해 대칭
            if (!(startX == endX && startY <= endY)) {
                distance = Math.min(distance, getDistance(startX, startY, endX, n + (n - endY)));
            }

            // 하: 반사된 목표를 하단 벽에 대해 대칭
            if (!(startX == endX && startY >= endY)) {
                distance = Math.min(distance, getDistance(startX, startY, endX, endY * (-1)));
            }

            answer[i] = distance;
        }

        return answer;
    }
}
