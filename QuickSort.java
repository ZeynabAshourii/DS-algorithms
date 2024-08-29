import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class QuickSort {
    static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    static void quickSort(int[] array, int low, int high) {
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();
            int pivot = partition(array, low, high);
            if (pivot - 1 > low) {
                stack.push(low);
                stack.push(pivot - 1);
            }
            if (pivot + 1 < high) {
                stack.push(pivot + 1);
                stack.push(high);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] string = reader.readLine().split(" ");
        int n = Integer.parseInt(string[0]);
        int m = Integer.parseInt(string[1]);
        String[] strings = reader.readLine().split(" ");
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < m; i++){
            long x = Long.valueOf(strings[i]);
            if (x != 0) {
                minHeap.add(x);
                maxHeap.add(x);
            }
        }
        long min = 0;
        for (int i = 0; i < n; i++){
            long root = minHeap.poll();
            if (root > 1){
                min += root;
                minHeap.add(root - 1);
            } else if (root == 1){
                min += 1;
            }
        }
        System.out.print(min + " ");
        long max = 0;
        for (int i = 0; i < n; i++){
            long root = maxHeap.poll();
            if (root > 1){
                max += root;
                maxHeap.add(root - 1);
            } else if (root == 1){
                max += 1;
            }
        }
        System.out.print(max);
    }
}

