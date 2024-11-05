package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, w, L;
    static int[] trucks, loc;
    static Queue<Integer> onBridge = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = input(st);
        w = input(st);
        L = input(st);
        st = new StringTokenizer(br.readLine());

        trucks = new int[n];
        for(int i=0; i<n; i++){
            trucks[i] = input(st);
        }

        int ans = 1;
        int idx = -1;
        int weight = 0;
        loc = new int[n];

        while(true){
            if(idx == n-1 && onBridge.isEmpty()) break;

            if(idx<n-1 && weight+trucks[idx+1] <= L){
                weight += trucks[++idx];
                onBridge.add(trucks[idx]);
            }
            for(int i=0; i<=idx; i++){
                loc[i]++;
                if(loc[i] == w){
                    weight -= onBridge.poll();
                }
            }
            ans++;
        }

        System.out.println(ans);
    }

    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }

}
