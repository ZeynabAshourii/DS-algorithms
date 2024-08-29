import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DiameterTree {
    static int bfs(ArrayList<ArrayList<Integer>> adj , int node , int n)
    {
        boolean[] vis = new boolean[n];
        int[] dis = new int[n];
        Queue<Integer> qu = new LinkedList<>();
        qu.add(node);
        dis[0] = 0;
        while (!qu.isEmpty())
        {
            int p = qu.peek();
            qu.remove();
            vis[p] = true;
            for (int child : adj.get(p))
            {
                if (!vis[child])
                {
                    dis[child] = dis[p] + 1;
                    qu.add(child);
                }
            }
        }
        int maxDistance = 0;
        int maxIndex = node;
        for (int i = 0; i < n; i++){
            if (dis[i] > maxDistance){
                maxDistance = dis[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    static int high(ArrayList<ArrayList<Integer>> adj ,int s ,  int n){
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        int distances = 0;
        int maxDistance = 0;
        int ver = s;
        while (!stack.empty()) {
            s = stack.pop();
            if (visited[s] == false) {
                System.out.println((s+1) + " ");
                if (adj.get(s).size() == 1){
                    if (maxDistance < distances){
                        maxDistance = distances;
                        ver = s;
                    }
                    distances = 0;
                }
                visited[s] = true;
                distances++;
            }
            for (int v : adj.get(s)) {
                if (visited[v] == false) {
                    stack.push(v);
                }
            }
        }
        return ver+1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < n-1; i++){
            String[] strings = reader.readLine().split(" ");
            int u = Integer.parseInt(strings[0]);
            int v = Integer.parseInt(strings[1]);
            adj.get(u-1).add(v-1);
            adj.get(v-1).add(u-1);
        }
        int u = bfs(adj , 0 , n);
        int v = bfs(adj , u , n);
        System.out.println((u+1) + " " +  (v+1));
        //   int u = high(adj , x , n);
        //   System.out.println(u + " " + high(adj , u-1, n));

    }
}

