import java.util.*;
import java.io.*;
public class Main {

    private static int N;
    private static int durabilityCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] durabilityArray = new int[N*2];
        Deque<Integer> downBeltQueue = new ArrayDeque<>();
        Deque<Integer> upBeltQueue = new ArrayDeque<>();
        Queue<Integer> robotIndexQueue = new ArrayDeque<>();
        boolean[] robotCheckArray = new boolean[N*2];
        for (int index = 0; index < N*2; index++) {
            durabilityArray[index] = Integer.parseInt(st.nextToken());
            if (index < N) {
                downBeltQueue.addFirst(index);
            } else {
                upBeltQueue.addFirst(index);
            }
        }

        int answer = 0;
        while(durabilityCount < K) {
            rotateBelt(downBeltQueue, upBeltQueue);
            moveRobot(robotIndexQueue, robotCheckArray, downBeltQueue.peekFirst(), durabilityArray);
            addRobot(downBeltQueue, robotIndexQueue, robotCheckArray, durabilityArray);
            answer++;
        }
        System.out.println(answer);
    }
    //

    private static void rotateBelt(Deque<Integer> downBeltQueue, Deque<Integer> upBeltQueue) {
        int downIndex = downBeltQueue.pollFirst();
        upBeltQueue.addLast(downIndex);
        int upIndex = upBeltQueue.pollFirst();
        downBeltQueue.addLast(upIndex);
    }

    private static void moveRobot(Queue<Integer> robotIndexQueue, boolean[] robotCheckArray, int downIndex, int[] durabilityArray) {
        int size = robotIndexQueue.size();
        int count = 0;
        while(count < size) {
            count++;
            int robotIndex = robotIndexQueue.poll();
            if(downIndex == robotIndex) {
                robotCheckArray[robotIndex] = false;
            } else {
                int nextIndex = robotIndex+1;
                if(nextIndex == N*2) {
                    nextIndex = 0;
                }
                if(!robotCheckArray[nextIndex] && durabilityArray[nextIndex] != 0) {
                    robotCheckArray[robotIndex] = false;
                    robotCheckArray[nextIndex] = true;
                    durabilityArray[nextIndex]--;
                    if(durabilityArray[nextIndex] == 0) {
                        durabilityCount++;
                    }
                    if(nextIndex != downIndex) {
                        robotIndexQueue.add(nextIndex);
                    }
                } else {
                    robotIndexQueue.add(robotIndex);
                }
            }


        }
    }
    private static void addRobot(Deque<Integer> downBeltQueue, Queue<Integer> robotIndexQueue, boolean[] robotCheckArray, int[] durabilityArray) {
        int upIndex = downBeltQueue.peekLast();
        if (durabilityArray[upIndex] != 0) {
            robotIndexQueue.add(upIndex);
            robotCheckArray[upIndex] = true;
            durabilityArray[upIndex] -= 1;
            if(durabilityArray[upIndex] == 0) {
                durabilityCount++;
            }
        }
    }

}