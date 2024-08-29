import java.io.*;
import java.util.*;

public class Points {
    public static class Point{
        static HashSet<Point> allPoints = new HashSet<>();
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            allPoints.add(this);
        }
    }
    static void solve (HashMap<Integer , HashSet<Integer>> plane , int start , int end , ArrayList<Integer> keys){
        if (start >= end){
            return;
        }

        int n = 0;
        for (int i = start; i<=end; i++){
            n+=plane.get(keys.get(i)).size();
        }

        int number = 0;
        int mid = 0;
        for (int i = start; i <= end; i++){
            number += plane.get(keys.get(i)).size();
            if (number > n/2){
                mid = i;
                break;
            }
        }
        if (start + 1 != end){
            solve(plane, start, mid - 1 , keys);
            solve(plane , mid+1 , end ,keys);
        }
        for (int i = start; i <= end; i++){
            if (i != mid) {
                for (Integer j : plane.get(keys.get(i))) {
                    if (!plane.get(keys.get(mid)).contains(j)) {
                        plane.get(keys.get(mid)).add(j);
                        new Point(keys.get(mid), j);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        HashMap<Integer , HashSet<Integer>> plane = new HashMap<>();
        int x , y;
        for (int i = 0; i < n; i++){
            String[] strings = reader.readLine().split(" ");
            x = Integer.parseInt(strings[0]);
            //  System.out.print(x + " ");
            y = Integer.parseInt(strings[1]);
            //   System.out.println(y);

            if (plane.containsKey(x)){
                plane.get(x).add(y);

            }else {
                HashSet<Integer> column = new HashSet<>();
                column.add(y);
                plane.put(x , column);
            }
        }
        ArrayList<Integer> keys = new ArrayList<Integer>(plane.keySet());
        keys.sort(Comparator.naturalOrder());
        solve(plane , 0 , plane.keySet().size()-1 , keys);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        out.write(Point.allPoints.size() + "\n");
        for (Point point : Point.allPoints){
            out.write(point.x + " " + point.y + "\n");
        }
        out.flush();

    }
}
